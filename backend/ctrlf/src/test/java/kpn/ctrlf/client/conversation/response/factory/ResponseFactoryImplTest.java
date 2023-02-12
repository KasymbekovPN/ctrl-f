package kpn.ctrlf.client.conversation.response.factory;

import kpn.ctrlf.client.conversation.response.Response;
import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
import kpn.ctrlf.client.conversation.response.value.Value;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResponseFactoryImplTest {
	private static final int EXPECTED = 123;
	private static final String CODE = "some.code";
	private static final Value VALUE = new TestValue(EXPECTED);
	private static final ErrorArgs ARGS = new TestErrorArgs(EXPECTED);

	@Test
	void shouldCheckSimpleResponseCreation() {
		boolean expected = true;
		Response response = new ResponseFactoryImpl().createSimpleResponse(expected);

		assertThat(response.isSuccess()).isEqualTo(expected);
		assertThat(response.getValue()).isNull();
		assertThat(response.getCode()).isNull();
		assertThat(response.getArgs()).isNull();
	}

	@Test
	void shouldCheckOkResponseCreation() {
		Response response = new ResponseFactoryImpl().createOkResponse(VALUE);

		assertThat(response.isSuccess()).isTrue();
		assertThat(response.getValue()).isEqualTo(VALUE);
		assertThat(response.getCode()).isNull();
		assertThat(response.getArgs()).isNull();
	}

	@Test
	void shouldCheckErrorResponseCreation() {
		Response response = new ResponseFactoryImpl().createErrorResponse(CODE);

		assertThat(response.isSuccess()).isFalse();
		assertThat(response.getValue()).isNull();
		assertThat(response.getCode()).isEqualTo(CODE);
		assertThat(response.getArgs()).isNull();
	}

	@Test
	void shouldCheckErrorArgsResponse() {
		Response response = new ResponseFactoryImpl().createErrorArgResponse(CODE, ARGS);

		assertThat(response.isSuccess()).isFalse();
		assertThat(response.getValue()).isNull();
		assertThat(response.getCode()).isEqualTo(CODE);
		assertThat(response.getArgs()).isEqualTo(ARGS);
	}

	@RequiredArgsConstructor
	@EqualsAndHashCode
	private static class TestValue implements Value {
		private final int i;
	}

	@RequiredArgsConstructor
	@EqualsAndHashCode
	private static class TestErrorArgs implements ErrorArgs {
		private final int i;
	}
}
