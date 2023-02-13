package kpn.ctrlf.client.conversation.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class AuthControllerRequest {
	private String username;
	private String password;
}
