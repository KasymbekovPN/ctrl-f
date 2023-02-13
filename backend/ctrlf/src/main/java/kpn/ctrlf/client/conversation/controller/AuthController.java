package kpn.ctrlf.client.conversation.controller;

import kpn.ctrlf.client.conversation.request.AuthControllerRequest;
import kpn.ctrlf.client.conversation.response.Response;
import kpn.ctrlf.client.conversation.response.args.AuthControllerErrorArgs;
import kpn.ctrlf.client.conversation.response.factory.ResponseFactory;
import kpn.ctrlf.client.conversation.response.value.AuthControllerValue;
import kpn.ctrlf.data.domain.User;
import kpn.ctrlf.secure.UserSecureService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.function.Function;

@RequiredArgsConstructor
@Controller
public final class AuthController implements RequestController<AuthControllerRequest> {
	private final ResponseFactory responseFactory;
	private final UserSecureService<User> userSecureService;
	private final Function<AuthControllerRequest, User> converter;

	@Override
	@MessageMapping("/authRequest/{sessionId}")
	@SendTo("/topic/authResponse/{sessionId}")
	public Response response(@DestinationVariable String sessionId, AuthControllerRequest request) {
		User user = converter.apply(request);
		if (userSecureService.checkCredential(user)) {
			return responseFactory.createOkResponse(new AuthControllerValue(sessionId, user.getUsername()));
		} else {
			return responseFactory.createErrorArgResponse("auth.wrong-credential", new AuthControllerErrorArgs(user.getUsername()));
		}
	}
}
