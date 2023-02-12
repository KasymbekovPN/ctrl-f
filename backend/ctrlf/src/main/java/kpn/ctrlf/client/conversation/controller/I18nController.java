package kpn.ctrlf.client.conversation.controller;

import kpn.ctrlf.client.i18n.I18nSource;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Map;

// TODO: 11.02.2023 remake returned
@Controller
@RequiredArgsConstructor
public final class I18nController implements RequestControllerOlf<I18nController.Request, I18nController.Response> {

	private final I18nSource i18nSource;

	@Override
	@MessageMapping("/i18nRequest/{sessionId}")
	@SendTo("/topic/i18nResponse/{sessionId}")
	public Response response(@DestinationVariable String sessionId,
							 Request request){
		// TODO: 12.02.2023 del
		System.out.println(this);
		return new Response(i18nSource.get());
	}
	public static  class Request {}

	@RequiredArgsConstructor
	@Getter
	public static class Response {
		private final Map<String, Map<String, String>> templates;
	}
}
