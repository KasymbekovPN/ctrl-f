package kpn.ctrlf.client.conversation.response.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class AuthControllerValue implements Value{
	private final String token;
	private final String username;
}
