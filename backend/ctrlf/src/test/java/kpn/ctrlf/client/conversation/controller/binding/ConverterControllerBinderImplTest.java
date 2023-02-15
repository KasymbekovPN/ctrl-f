package kpn.ctrlf.client.conversation.controller.binding;

import kpn.ctrlf.client.conversation.controller.ControllerConverter;
import kpn.ctrlf.client.conversation.controller.Controllers;
import kpn.ctrlf.client.conversation.controller.RequestController;
import kpn.ctrlf.client.conversation.response.Response;
import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
import kpn.ctrlf.client.conversation.response.converter.args.ErrorArgsConverter;
import kpn.ctrlf.client.conversation.response.converter.value.ValueConverter;
import kpn.ctrlf.client.conversation.response.value.Value;
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

	@Test
	void shouldCheckValueConverterAddition_ifControllerWithoutAnnotation() {

	}

	@Test
	void shouldCheckValueConverterAddition() {

	}

	@Test
	void shouldCheckErrorArgsAddition_ifControllerWithoutAnnotation() {

	}

	@Test
	void shouldCheckErrorArgsConverterAddition() {
	}

	@Test
	void shouldCheckBondingAttempt_ifValueConverterIsAbsent() {

	}

	@Test
	void shouldCheckBindingAttempt_ifErrorArgsConverterIsAbsent() {

	}

	@Test
	void shouldCheckBindingAttempt_ifValueAndErrorArgsConvertersAreAbsent() {

	}

	@Test
	void shouldCheckBindingAttempt_ifSeveralValueConverterFields() {

	}

	@Test
	void shouldCheckBindingAttempt_ifSeveralErrorArgsConverterFields() {

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
}
