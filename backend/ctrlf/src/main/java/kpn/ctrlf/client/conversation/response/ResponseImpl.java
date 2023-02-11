package kpn.ctrlf.client.conversation.response;

import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
import kpn.ctrlf.client.conversation.response.value.Value;
import lombok.Getter;

@Getter
public final class ResponseImpl implements Response {
	private final boolean success;
	private final Value value;
	private final String code;
	private final ErrorArgs args;

	public ResponseImpl(Value value) {
		this.success = true;
		this.value = value;
		this.code = null;
		this.args = null;
	}

	public ResponseImpl(String code, ErrorArgs args) {
		this.success = false;
		this.value = null;
		this.code = code;
		this.args = args;
	}
}