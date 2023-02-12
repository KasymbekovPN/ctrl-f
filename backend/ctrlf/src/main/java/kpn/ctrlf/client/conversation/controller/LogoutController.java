package kpn.ctrlf.client.conversation.controller;

import kpn.ctrlf.client.conversation.request.EmptyRequest;
import kpn.ctrlf.client.conversation.response.Response;
import kpn.ctrlf.client.conversation.response.ResponseImpl;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public final class LogoutController implements RequestController<EmptyRequest> {
	@Override
	@MessageMapping("/logoutRequest/{sessionId}")
	@SendTo("/topic/logoutResponse/{sessionId}")
	public Response response(String sessionId, EmptyRequest request) {
		return new ResponseImpl(null);
	}
}
