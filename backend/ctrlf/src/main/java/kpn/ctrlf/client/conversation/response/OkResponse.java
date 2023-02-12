package kpn.ctrlf.client.conversation.response;

import kpn.ctrlf.client.conversation.response.value.Value;
import lombok.Getter;

@Getter
public final class OkResponse extends SimpleResponse{
	private final Value value;

	public OkResponse(Value value) {
		super(true);
		this.value = value;
	}
}
