package kpn.ctrlf.client.conversation.response.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class TagCreationControllerValue implements Value {
	private final Long id; // TODO: 12.02.2023 is it need ???
	private final String name;
}
