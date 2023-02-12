package kpn.ctrlf.client.conversation.controller;

import kpn.ctrlf.client.conversation.request.EmptyRequest;
import kpn.ctrlf.client.conversation.response.Response;
import kpn.ctrlf.client.conversation.response.factory.ResponseFactory;
import kpn.ctrlf.client.conversation.response.value.I18nControllerValue;
import kpn.ctrlf.client.i18n.I18nSource;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public final class I18nController implements RequestController<EmptyRequest>{
	private final ResponseFactory responseFactory;
	private final I18nSource i18nSource;

	@Override
	@MessageMapping("/i18nRequest/{sessionId}")
	@SendTo("/topic/i18nResponse/{sessionId}")
	public Response response(@DestinationVariable String sessionId, EmptyRequest request) {
		return responseFactory.createOkResponse(new I18nControllerValue(i18nSource.get()));
	}
}
