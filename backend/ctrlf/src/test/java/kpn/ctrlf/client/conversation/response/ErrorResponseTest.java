package kpn.ctrlf.client.conversation.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorResponseTest {

	private static final String CODE = "some.code";

	@Test
	void shouldCheckSuccessGetting() {
		ErrorResponse response = new ErrorResponse(CODE);
		assertThat(response.isSuccess()).isFalse();
	}

	@Test
	void shouldCheckCodeGetting() {
		ErrorResponse response = new ErrorResponse(CODE);
		assertThat(response.getCode()).isEqualTo(CODE);
	}
}
