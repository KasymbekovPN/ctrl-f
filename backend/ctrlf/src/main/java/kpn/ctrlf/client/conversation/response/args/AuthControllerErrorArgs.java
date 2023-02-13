package kpn.ctrlf.client.conversation.response.args;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class AuthControllerErrorArgs implements ErrorArgs{
	private final String username;
}
