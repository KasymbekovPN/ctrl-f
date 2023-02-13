package kpn.ctrlf.client.conversation.response.args;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AuthControllerErrorArgsTest {

	@Test
	void shouldCheckUsernameGetting() {
		String expected = "some username";
		String username = new AuthControllerErrorArgs(expected).getUsername();

		assertThat(username).isEqualTo(expected);
	}
}
