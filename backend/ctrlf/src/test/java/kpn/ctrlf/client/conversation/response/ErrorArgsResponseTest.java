package kpn.ctrlf.client.conversation.response;

import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorArgsResponseTest {
	private static final int EXPECTED = 1;
	private static final String CODE = "some.code";
	private static final ErrorArgs ARGS = new TestErrorArgs(EXPECTED);

	@Test
	void shouldCheckSuccessGetting() {
		ErrorArgsResponse response = new ErrorArgsResponse(CODE, ARGS);
		assertThat(response.isSuccess()).isFalse();
	}

	@Test
	void shouldCheckCodeGetting() {
		ErrorArgsResponse response = new ErrorArgsResponse(CODE, ARGS);
		assertThat(response.getCode()).isEqualTo(CODE);
	}

	@Test
	void shouldCheckArgsGetting() {
		ErrorArgsResponse response = new ErrorArgsResponse(CODE, ARGS);
		assertThat(response.getArgs()).isEqualTo(ARGS);
	}

	@RequiredArgsConstructor
	@EqualsAndHashCode
	@Getter
	private static class TestErrorArgs implements ErrorArgs {
		private final int i;
	}
}
