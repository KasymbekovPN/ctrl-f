package kpn.ctrlf.client.conversation.controller;

import kpn.ctrlf.client.conversation.request.EmptyRequest;
import kpn.ctrlf.client.conversation.response.Response;
import kpn.ctrlf.client.conversation.response.factory.ResponseFactory;
import kpn.ctrlf.client.conversation.response.value.ClientParamsControllerValue;
import kpn.ctrlf.client.params.ClientParams;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public final class ClientParamsController implements RequestController<EmptyRequest> {
	private final ResponseFactory responseFactory;
	private final ClientParams clientParams;

	@Override
	@MessageMapping("/clientParamsRequest/{sessionId}")
	@SendTo("/topic/clientParamsResponse/{sessionId}")
	public Response response(@DestinationVariable String sessionId, EmptyRequest request) {
		return responseFactory.createOkResponse(new ClientParamsControllerValue(clientParams.getLocale()));
	}
}
