package kpn.ctrlf.client.conversation.response.args;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TagLoadingControllerErrorArgsTest {

	@Test
	void shouldCheckGetting() {
		String expectedCause = "some.cause";
		String cause = new TagLoadingControllerErrorArgs(expectedCause).getCause();

		assertThat(cause).isEqualTo(expectedCause);
	}
}
