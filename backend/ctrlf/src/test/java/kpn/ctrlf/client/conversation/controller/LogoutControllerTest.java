package kpn.ctrlf.client.conversation.controller;

import kpn.ctrlf.client.conversation.request.EmptyRequest;
import kpn.ctrlf.client.conversation.response.Response;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LogoutControllerTest {
	@Test
	void shouldCheckResponseMethod() {
		LogoutController controller = new LogoutController();
		Response response = controller.response("", new EmptyRequest());

		assertThat(response.isSuccess()).isTrue();
		assertThat(response.getValue()).isNull();
		assertThat(response.getCode()).isNull();
		assertThat(response.getArgs()).isNull();
	}
}
