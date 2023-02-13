package kpn.ctrlf.client.conversation.request.converter;

import kpn.ctrlf.client.conversation.request.AuthControllerRequest;
import kpn.ctrlf.data.domain.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public final class AuthControllerRequestToUserConverter implements Function<AuthControllerRequest, User> {

	@Override
	public User apply(AuthControllerRequest request) {
		return new User(request.getUsername(), request.getPassword());
	}
}
