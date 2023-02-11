package kpn.ctrlf.client.conversation.response.converter;

import kpn.ctrlf.client.conversation.response.Response;
import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
import kpn.ctrlf.client.conversation.response.converter.args.ErrorArgsConverter;
import kpn.ctrlf.client.conversation.response.converter.value.ValueConverter;
import kpn.ctrlf.client.conversation.response.value.Value;
import kpn.lib.result.ImmutableResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class ResponseConverterImplTest {
	private static final String CODE = "some.code";
	private static final Integer VALUE = 1;
	private static final Integer ARG = 2;

	@Test
	void shouldCheckConversion_ifResultSuccessIsFalse() {
		ImmutableResult<Integer> result = ImmutableResult.<Integer>bFail(CODE).arg(ARG).build();
		ResponseConverterImpl converter = new ResponseConverterImpl();

		Response response = converter.convert(result, createValueConverter(), createErrorConverter());
		assertThat(response.isSuccess()).isFalse();
		assertThat(response.getValue()).isNull();
		assertThat(response.getCode()).isEqualTo(CODE);
		assertThat(response.getArgs().getClass()).isEqualTo(TestErrorArgs.class);
		TestErrorArgs args = (TestErrorArgs) response.getArgs();
		assertThat(args.getI()).isEqualTo(ARG);
	}

	@Test
	void shouldCheckConversion_ifResultSuccessIsTrue() {
		ImmutableResult<Integer> result = ImmutableResult.<Integer>ok(VALUE);
		ResponseConverterImpl converter = new ResponseConverterImpl();

		Response response = converter.convert(result, createValueConverter(), createErrorConverter());
		assertThat(response.isSuccess()).isTrue();
		assertThat(response.getCode()).isNull();
		assertThat(response.getArgs()).isNull();
		assertThat(response.getValue().getClass()).isEqualTo(TestValue.class);
		TestValue value = (TestValue) response.getValue();
		assertThat(value.getI()).isEqualTo(VALUE);
	}

	private ValueConverter createValueConverter(){
		TestValueConverter converter = Mockito.mock(TestValueConverter.class);
		Mockito
			.when(converter.convert(VALUE))
			.thenReturn(new TestValue(VALUE));

		return  converter;
	}

	private ErrorArgsConverter createErrorConverter() {
		TestErrorArgsConverter converter = Mockito.mock(TestErrorArgsConverter.class);
		Mockito
			.when(converter.convert(ARG))
			.thenReturn(new TestErrorArgs(ARG));

		return converter;
	}

	private static abstract class TestValueConverter implements ValueConverter {}
	private static abstract class TestErrorArgsConverter implements ErrorArgsConverter {}

	@RequiredArgsConstructor
	@Getter
	private static class TestValue implements Value {
		private final int i;
	}

	@RequiredArgsConstructor
	@Getter
	private static class TestErrorArgs implements ErrorArgs {
		private final int i;
	}
}
