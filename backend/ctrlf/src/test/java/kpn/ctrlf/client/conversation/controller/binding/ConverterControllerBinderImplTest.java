package kpn.ctrlf.client.conversation.controller.binding;

import kpn.ctrlf.client.conversation.controller.ControllerConverter;
import kpn.ctrlf.client.conversation.controller.Controllers;
import kpn.ctrlf.client.conversation.controller.RequestController;
import kpn.ctrlf.client.conversation.response.Response;
import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
import kpn.ctrlf.client.conversation.response.converter.args.ErrorArgsConverter;
import kpn.ctrlf.client.conversation.response.converter.value.ValueConverter;
import kpn.ctrlf.client.conversation.response.value.Value;
import kpn.lib.result.ImmutableResult;
import kpn.lib.result.Result;
import lombok.Getter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.EnumMap;

import static org.assertj.core.api.Assertions.assertThat;

class ConverterControllerBinderImplTest {

	@SneakyThrows
	@Test
	void shouldCheckControllerAddition_ifControllerWithoutAnnotation() {
		ConverterControllerBinder binder = new ConverterControllerBinderImpl();
		binder.addController(new TestRequestControllerWithoutAnnotation());

		Field field = binder.getClass().getDeclaredField("controllers");
		field.setAccessible(true);
		EnumMap<Controllers, RequestController<?>> controllers = (EnumMap<Controllers, RequestController<?>>) field.get(binder);

		assertThat(controllers).isEmpty();
	}

	@SneakyThrows
	@Test
	void shouldCheckControllerAddition() {
		TestRequestController controller = new TestRequestController();
		ConverterControllerBinder binder = new ConverterControllerBinderImpl();
		binder.addController(controller);

		Field field = binder.getClass().getDeclaredField("controllers");
		field.setAccessible(true);
		EnumMap<Controllers, RequestController<?>> controllers = (EnumMap<Controllers, RequestController<?>>) field.get(binder);

		EnumMap<Controllers, RequestController<?>> expected = new EnumMap<>(Controllers.class);
		expected.put(Controllers.TAG_CREATION, controller);

		assertThat(controllers).isEqualTo(expected);
	}

	@SneakyThrows
	@Test
	void shouldCheckValueConverterAddition_ifConverterWithoutAnnotation() {
		ConverterControllerBinder binder = new ConverterControllerBinderImpl();
		binder.addValueConverter(new TestValueConverterWithoutAnnotation());

		Field field = binder.getClass().getDeclaredField("valueConverters");
		field.setAccessible(true);
		EnumMap<Controllers, ValueConverter> valueConverters = (EnumMap<Controllers, ValueConverter>) field.get(binder);

		assertThat(valueConverters).isEmpty();
	}

	@SneakyThrows
	@Test
	void shouldCheckValueConverterAddition() {
		TestValueConverter converter = new TestValueConverter();
		ConverterControllerBinder binder = new ConverterControllerBinderImpl();
		binder.addValueConverter(converter);

		Field field = binder.getClass().getDeclaredField("valueConverters");
		field.setAccessible(true);
		EnumMap<Controllers, ValueConverter> valueConverters = (EnumMap<Controllers, ValueConverter>) field.get(binder);

		EnumMap<Controllers, ValueConverter> expected = new EnumMap<>(Controllers.class);
		expected.put(Controllers.TAG_CREATION, converter);

		assertThat(valueConverters).isEqualTo(expected);
	}

	@SneakyThrows
	@Test
	void shouldCheckErrorArgsAddition_ifConverterWithoutAnnotation() {
		ConverterControllerBinder binder = new ConverterControllerBinderImpl();
		binder.addErrorArgsConverter(new TestErrorArgsConverterWithoutAnnotation());

		Field field = binder.getClass().getDeclaredField("errorArgsConverters");
		field.setAccessible(true);
		EnumMap<Controllers, ErrorArgsConverter> errorArgsConverters = (EnumMap<Controllers, ErrorArgsConverter>) field.get(binder);

		assertThat(errorArgsConverters).isEmpty();
	}

	@SneakyThrows
	@Test
	void shouldCheckErrorArgsConverterAddition() {
		TestErrorArgsConverter converter = new TestErrorArgsConverter();
		ConverterControllerBinder binder = new ConverterControllerBinderImpl();
		binder.addErrorArgsConverter(converter);

		Field field = binder.getClass().getDeclaredField("errorArgsConverters");
		field.setAccessible(true);
		EnumMap<Controllers, ErrorArgsConverter> valueConverters = (EnumMap<Controllers, ErrorArgsConverter>) field.get(binder);

		EnumMap<Controllers, ErrorArgsConverter> expected = new EnumMap<>(Controllers.class);
		expected.put(Controllers.TAG_CREATION, converter);

		assertThat(valueConverters).isEqualTo(expected);
	}

	@Test
	void shouldCheckBondingAttempt_ifValueConverterIsAbsent() {
		TestErrorArgsConverter converter = new TestErrorArgsConverter();
		TestRequestController controller = new TestRequestController();
		ConverterControllerBinderImpl binder = new ConverterControllerBinderImpl();
		binder.addController(controller);
		binder.addErrorArgsConverter(converter);

		String code = "Controller " + Controllers.TAG_CREATION.name() + ": value converter is absent";
		ImmutableResult<Void> expectedResult = ImmutableResult.<Void>fail(code);

		Result<Void> result = binder.bind();
		assertThat(result).isEqualTo(expectedResult);
	}

	@Test
	void shouldCheckBindingAttempt_ifErrorArgsConverterIsAbsent() {
		TestValueConverter converter = new TestValueConverter();
		TestRequestController controller = new TestRequestController();
		ConverterControllerBinderImpl binder = new ConverterControllerBinderImpl();
		binder.addController(controller);
		binder.addValueConverter(converter);

		String code = "Controller " + Controllers.TAG_CREATION.name() + ": error converter is absent";
		ImmutableResult<Void> expectedResult = ImmutableResult.<Void>fail(code);

		Result<Void> result = binder.bind();
		assertThat(result).isEqualTo(expectedResult);
	}

	@Test
	void shouldCheckBindingAttempt_ifValueAndErrorArgsConvertersAreAbsent() {
		TestRequestController controller = new TestRequestController();
		ConverterControllerBinderImpl binder = new ConverterControllerBinderImpl();
		binder.addController(controller);

		String code = "Controller " + Controllers.TAG_CREATION.name() + ": value & error converters is absent";
		ImmutableResult<Void> expectedResult = ImmutableResult.<Void>fail(code);

		Result<Void> result = binder.bind();
		assertThat(result).isEqualTo(expectedResult);
	}

	@Test
	void shouldCheckBindingAttempt_ifSeveralValueConverterFields() {
		TestRequestControllerWithSeveralValueConverter controller = new TestRequestControllerWithSeveralValueConverter();
		ConverterControllerBinderImpl binder = new ConverterControllerBinderImpl();
		binder.addController(controller);

		String code = "Controller " + Controllers.TAG_CREATION.name() + ": it has several ValueConverter fields";
		ImmutableResult<Void> expectedResult = ImmutableResult.<Void>fail(code);

		Result<Void> result = binder.bind();
		assertThat(result).isEqualTo(expectedResult);
	}

	@Test
	void shouldCheckBindingAttempt_ifSeveralErrorArgsConverterFields() {
		TestRequestControllerWithSeveralErrorArgsConverter controller = new TestRequestControllerWithSeveralErrorArgsConverter();
		ConverterControllerBinderImpl binder = new ConverterControllerBinderImpl();
		binder.addController(controller);

		String code = "Controller " + Controllers.TAG_CREATION.name() + ": it has several ErrorArgsConverter fields";
		ImmutableResult<Void> expectedResult = ImmutableResult.<Void>fail(code);

		Result<Void> result = binder.bind();
		assertThat(result).isEqualTo(expectedResult);
	}

	@Test
	void shouldCheckBindingAttempt_ifSeveralValueAndErrorArgsConverterFields() {
		TestRequestControllerWithSeveralValueAndErrorArgsConverter controller = new TestRequestControllerWithSeveralValueAndErrorArgsConverter();
		ConverterControllerBinderImpl binder = new ConverterControllerBinderImpl();
		binder.addController(controller);

		String code = "Controller " + Controllers.TAG_CREATION.name() + ": it has several ValueConverter & ErrorArgsConverter fields";
		ImmutableResult<Void> expectedResult = ImmutableResult.<Void>fail(code);

		Result<Void> result = binder.bind();
		assertThat(result).isEqualTo(expectedResult);
	}

	@Test
	void shouldCheckBinding() {

	}

	@ControllerConverter(Controllers.TAG_CREATION)
	@Getter
	public static class TestRequestController implements RequestController<String> {
		private ValueConverter valueConverter;
		private ErrorArgsConverter errorArgsConverter;

		private String name;

		@Override
		public Response response(String sessionId, String request) { return null; }
	}

	public static class TestRequestControllerWithoutAnnotation implements RequestController<String> {
		@Override
		public Response response(String sessionId, String request) { return null; }
	}

	@ControllerConverter(Controllers.TAG_CREATION)
	public static class TestValueConverter implements ValueConverter {
		@Override
		public Value convert(Object input) { return null; }
	}

	public static class TestValueConverterWithoutAnnotation implements ValueConverter {
		@Override
		public Value convert(Object input) { return null; }
	}

	@ControllerConverter(Controllers.TAG_CREATION)
	public static class TestErrorArgsConverter implements ErrorArgsConverter {
		@Override
		public ErrorArgs convert(Object... input) {
			return null;
		}
	}

	public static class TestErrorArgsConverterWithoutAnnotation implements ErrorArgsConverter {
		@Override
		public ErrorArgs convert(Object... input) {
			return null;
		}
	}

	@ControllerConverter(Controllers.TAG_CREATION)
	public static class TestRequestControllerWithSeveralValueConverter implements RequestController<String> {
		private ValueConverter valueConverter0;
		private ValueConverter valueConverter1;
		private ErrorArgsConverter errorArgsConverter;

		private String name;

		@Override
		public Response response(String sessionId, String request) { return null; }
	}

	@ControllerConverter(Controllers.TAG_CREATION)
	public static class TestRequestControllerWithSeveralErrorArgsConverter implements RequestController<String> {
		private ValueConverter valueConverter;
		private ErrorArgsConverter errorArgsConverter0;
		private ErrorArgsConverter errorArgsConverter1;

		private String name;

		@Override
		public Response response(String sessionId, String request) { return null; }
	}

	@ControllerConverter(Controllers.TAG_CREATION)
	public static class TestRequestControllerWithSeveralValueAndErrorArgsConverter implements RequestController<String> {
		private ValueConverter valueConverter0;
		private ValueConverter valueConverter1;
		private ErrorArgsConverter errorArgsConverter0;
		private ErrorArgsConverter errorArgsConverter1;

		private String name;

		@Override
		public Response response(String sessionId, String request) { return null; }
	}
}
