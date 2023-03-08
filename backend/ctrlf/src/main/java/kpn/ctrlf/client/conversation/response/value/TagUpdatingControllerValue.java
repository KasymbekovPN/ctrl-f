package kpn.ctrlf.client.conversation.response.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class TagUpdatingControllerValue implements Value{
	private final Long id;
	private final String name;
}
