package kpn.ctrlf.client.conversation.controller;

import kpn.ctrlf.client.conversation.request.EmptyRequest;
import kpn.ctrlf.client.conversation.response.Response;
import kpn.ctrlf.client.conversation.response.factory.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public final class LogoutController implements RequestController<EmptyRequest> {
	private final ResponseFactory responseFactory;

	@Override
	@MessageMapping("/logoutRequest/{sessionId}")
	@SendTo("/topic/logoutResponse/{sessionId}")
	public Response response(String sessionId, EmptyRequest request) {
		return responseFactory.createSimpleResponse(true);
	}
}
