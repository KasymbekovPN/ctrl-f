package kpn.ctrlf.client.conversation.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AuthControllerRequestTest {

	@Test
	void shouldCheckUsernameGetting() {
		String expected = "some.username";
		AuthControllerRequest request = new AuthControllerRequest();
		request.setUsername(expected);

		assertThat(request.getUsername()).isEqualTo(expected);
	}

	@Test
	void shouldCheckPasswordGetting() {
		String expected = "some password";
		AuthControllerRequest request = new AuthControllerRequest();
		request.setPassword(expected);

		assertThat(request.getPassword()).isEqualTo(expected);
	}
}
