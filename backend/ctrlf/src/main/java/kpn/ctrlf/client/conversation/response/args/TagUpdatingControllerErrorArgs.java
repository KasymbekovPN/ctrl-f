package kpn.ctrlf.client.conversation.response.args;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class TagUpdatingControllerErrorArgs implements ErrorArgs{
	private final String id;
	private final String name;
}
