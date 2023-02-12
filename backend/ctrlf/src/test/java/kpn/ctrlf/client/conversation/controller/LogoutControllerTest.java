package kpn.ctrlf.client.conversation.controller;

import kpn.ctrlf.client.conversation.request.EmptyRequest;
import kpn.ctrlf.client.conversation.response.SimpleResponse;
import kpn.ctrlf.client.conversation.response.factory.ResponseFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LogoutControllerTest {
	@Test
	void shouldCheckResponseMethod() {
		LogoutController controller = new LogoutController(new ResponseFactoryImpl());
		SimpleResponse response = (SimpleResponse) controller.response("", new EmptyRequest());

		assertThat(response.isSuccess()).isTrue();
	}
}
