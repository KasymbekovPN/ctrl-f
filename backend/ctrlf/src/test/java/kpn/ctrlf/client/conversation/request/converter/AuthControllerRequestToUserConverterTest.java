package kpn.ctrlf.client.conversation.request.converter;

import kpn.ctrlf.client.conversation.request.AuthControllerRequest;
import kpn.ctrlf.data.domain.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AuthControllerRequestToUserConverterTest {

	@Test
	void shouldCheckConversion() {
		String expectedUsername = "some username";
		String expectedPassword = "some password";
		AuthControllerRequest request = new AuthControllerRequest();
		request.setUsername(expectedUsername);
		request.setPassword(expectedPassword);

		User user = new AuthControllerRequestToUserConverter().apply(request);
		assertThat(user).isEqualTo(new User(expectedUsername, expectedPassword));
	}
}
