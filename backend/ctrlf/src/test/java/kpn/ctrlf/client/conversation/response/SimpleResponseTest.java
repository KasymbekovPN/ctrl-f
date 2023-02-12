package kpn.ctrlf.client.conversation.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleResponseTest {

	@Test
	void shouldCheckSuccessGetting() {
		boolean expected = true;
		SimpleResponse response = new SimpleResponse(expected);

		assertThat(response.isSuccess()).isEqualTo(expected);
	}
}
