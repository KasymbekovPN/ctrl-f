package kpn.ctrlf.client.conversation.response.value;

import kpn.ctrlf.data.domain.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class TagLoadingControllerValue implements Value{
	private final Tag[] tags;
}
