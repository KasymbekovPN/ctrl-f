package kpn.ctrlf.client.conversation.response.converter;

import kpn.ctrlf.client.conversation.response.ErrorArgsResponse;
import kpn.ctrlf.client.conversation.response.ErrorResponse;
import kpn.ctrlf.client.conversation.response.OkResponse;
import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
import kpn.ctrlf.client.conversation.response.converter.args.ErrorArgsConverter;
import kpn.ctrlf.client.conversation.response.converter.value.ValueConverter;
import kpn.ctrlf.client.conversation.response.factory.ResponseFactory;
import kpn.ctrlf.client.conversation.response.factory.ResponseFactoryImpl;
import kpn.ctrlf.client.conversation.response.value.Value;
import kpn.lib.result.ImmutableResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class ResponseConverterImplTest {
	private static final ResponseFactory RESPONSE_FACTORY = new ResponseFactoryImpl();
	private static final String CODE = "some.code";
	private static final Integer VALUE = 1;
	private static final Integer ARG = 2;

	@Test
	void shouldCheckConversion_ifResultSuccessIsFalseWithoutArgs() {
		ImmutableResult<Integer> result = ImmutableResult.<Integer>bFail(CODE).build();
		ResponseConverterImpl converter = new ResponseConverterImpl(RESPONSE_FACTORY);

		ErrorResponse response = (ErrorResponse) converter.convert(result, createValueConverter(), createErrorConverter());
		assertThat(response.isSuccess()).isFalse();
		assertThat(response.getCode()).isEqualTo(CODE);
	}

	@Test
	void shouldCheckConversion_ifResultSuccessIsFalse() {
		ImmutableResult<Integer> result = ImmutableResult.<Integer>bFail(CODE).arg(ARG).build();
		ResponseConverterImpl converter = new ResponseConverterImpl(RESPONSE_FACTORY);

		ErrorArgsResponse response = (ErrorArgsResponse) converter.convert(result, createValueConverter(), createErrorConverter());
		assertThat(response.isSuccess()).isFalse();
		assertThat(response.getCode()).isEqualTo(CODE);
		assertThat(response.getArgs().getClass()).isEqualTo(TestErrorArgs.class);
		TestErrorArgs args = (TestErrorArgs) response.getArgs();
		assertThat(args.getI()).isEqualTo(ARG);
	}

	@Test
	void shouldCheckConversion_ifResultSuccessIsTrue() {
		ImmutableResult<Integer> result = ImmutableResult.<Integer>ok(VALUE);
		ResponseConverterImpl converter = new ResponseConverterImpl(RESPONSE_FACTORY);

		OkResponse response = (OkResponse) converter.convert(result, createValueConverter(), createErrorConverter());
		assertThat(response.isSuccess()).isTrue();
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
