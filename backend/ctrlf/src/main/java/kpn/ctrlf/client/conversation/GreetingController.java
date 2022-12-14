package kpn.ctrlf.client.conversation;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public final class GreetingController {

	@MessageMapping("/greetingRequest")
	@SendTo("/topic/greetingResponse")
	public Response greetingResponse(Request request){
		return new Response();
	}

	public static class Request {}
	public static class Response {}
}
