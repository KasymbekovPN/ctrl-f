package kpn.ctrlf.client.conversation.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimpleResponse implements Response{
	@Getter
	private final boolean success;
}
