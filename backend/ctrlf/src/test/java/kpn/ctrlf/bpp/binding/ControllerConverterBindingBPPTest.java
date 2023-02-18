package kpn.ctrlf.bpp.binding;

import kpn.ctrlf.client.conversation.controller.RequestController;
import kpn.ctrlf.client.conversation.controller.binding.ConverterControllerBinder;
import kpn.ctrlf.client.conversation.response.Response;
import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
import kpn.ctrlf.client.conversation.response.converter.args.ErrorArgsConverter;
import kpn.ctrlf.client.conversation.response.converter.value.ValueConverter;
import kpn.ctrlf.client.conversation.response.value.Value;
import kpn.lib.result.Result;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ControllerConverterBindingBPPTest {

	@Test
	void shouldCheckIsRequestController_ifFail() {
		ControllerConverterBindingBPP.IsRequestController predicate
			= new ControllerConverterBindingBPP.IsRequestController();

		assertThat(predicate.test(new Object())).isFalse();
	}

	@Test
	void shouldCheckIsRequestController_ifSuccess() {
		ControllerConverterBindingBPP.IsRequestController predicate
			= new ControllerConverterBindingBPP.IsRequestController();

		assertThat(predicate.test(new TestRequestController())).isTrue();
	}

	@Test
	void shouldCheckIsValueConverter_ifFail() {
		ControllerConverterBindingBPP.IsValueConverter predicate
			= new ControllerConverterBindingBPP.IsValueConverter();

		assertThat(predicate.test(new Object())).isFalse();
	}

	@Test
	void shouldCheckIsValueConverter_ifSuccess() {
		ControllerConverterBindingBPP.IsValueConverter predicate
			= new ControllerConverterBindingBPP.IsValueConverter();

		assertThat(predicate.test(new TestValueConverter())).isTrue();
	}

	@Test
	void shouldCheckIsErrorArgsConverter_ifFail() {
		ControllerConverterBindingBPP.IsErrorArgsConverter predicate
			= new ControllerConverterBindingBPP.IsErrorArgsConverter();

		assertThat(predicate.test(new Object())).isFalse();
	}

	@Test
	void shouldCheckIsErrorArgsConverter_ifSuccess() {
		ControllerConverterBindingBPP.IsErrorArgsConverter predicate
			= new ControllerConverterBindingBPP.IsErrorArgsConverter();

		assertThat(predicate.test(new TestErrorConverter())).isTrue();
	}

	@Test
	void shouldCheckBPP() {
		TestRequestController controller = new TestRequestController();
		TestValueConverter valueConverter = new TestValueConverter();
		TestErrorConverter errorConverter = new TestErrorConverter();

		TestBinder binder = new TestBinder();
		ControllerConverterBindingBPP bpp = new ControllerConverterBindingBPP();
		bpp.setBinder(binder);

		ArrayList<Object> initList = new ArrayList<>(){{
			add("0");
			add(controller);
			add("1");
			add(valueConverter);
			add("2");
			add(errorConverter);
			add("3");
		}};
		for (Object bean : initList) {
			bpp.postProcessBeforeInitialization(bean, "");
		}

		ArrayList<Object> expected = new ArrayList<>(){{
			add(controller);
			add(valueConverter);
			add(errorConverter);
		}};
		assertThat(binder.getObjects()).isEqualTo(expected);
	}

	private static class TestRequestController implements RequestController<Object> {
		@Override
		public Response response(String sessionId, Object request) { return null; }
	}

	private static class TestValueConverter implements ValueConverter {
		@Override
		public Value convert(Object input) { return null; }
	}

	private static class TestErrorConverter implements ErrorArgsConverter {
		@Override
		public ErrorArgs convert(Object... input) { return null; }
	}

	private static class TestBinder implements ConverterControllerBinder {
		@Getter
		private final List<Object> objects = new ArrayList<>();

		@Override
		public void addController(RequestController<?> controller) {
			this.objects.add(controller);
		}

		@Override
		public void addValueConverter(ValueConverter converter) {
			this.objects.add(converter);
		}

		@Override
		public void addErrorArgsConverter(ErrorArgsConverter converter) {
			this.objects.add(converter);
		}

		@Override
		public Result<Void> bind() { return null; }
	}
}
