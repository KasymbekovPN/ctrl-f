package kpn.ctrlf.client.conversation.controller;

import kpn.ctrlf.client.params.ClientParams;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

// TODO: 11.02.2023 remake returned
@Controller
@RequiredArgsConstructor
public final class ClientParamsController implements RequestControllerOlf<ClientParamsController.Request, ClientParamsController.Response> {

	private final ClientParams clientParams;

	@Override
	@MessageMapping("/clientParamsRequest/{sessionId}")
	@SendTo("/topic/clientParamsResponse/{sessionId}")
	public Response response(@DestinationVariable String sessionId,
							 Request request){
		// TODO: 12.02.2023 del
		System.out.println(this);
		return new Response(clientParams.getLocale());
	}

	public static class Request {}

	@RequiredArgsConstructor
	public static class Response {
		private final String locale;

		public String getLocale() {
			return locale;
		}
	}
}
