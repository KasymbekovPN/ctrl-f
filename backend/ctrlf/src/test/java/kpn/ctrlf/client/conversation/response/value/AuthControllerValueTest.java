package kpn.ctrlf.client.conversation.response.value;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AuthControllerValueTest {
	private static final String TOKEN = "some.token";
	private static final String USER_NAME = "some.username";

	@Test
	void shouldCheckTokenGetting() {
		String token = new AuthControllerValue(TOKEN, USER_NAME).getToken();

		assertThat(token).isEqualTo(TOKEN);
	}

	@Test
	void shouldCheckUsernameGetting() {
		String username = new AuthControllerValue(TOKEN, USER_NAME).getUsername();

		assertThat(username).isEqualTo(USER_NAME);
	}
}
