package kpn.ctrlf.client.conversation.response;

import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
import kpn.ctrlf.client.conversation.response.value.Value;
import lombok.Getter;

@Getter
public class ResponseImpl<V extends Value, E extends ErrorArgs> implements Response<V, E> {
	private final boolean success;
	private final V value;
	private final String code;
	private final E args;

	public ResponseImpl(V value) {
		this.success = true;
		this.value = value;
		this.code = null;
		this.args = null;
	}

	public ResponseImpl(String code, E args) {
		this.success = false;
		this.value = null;
		this.code = code;
		this.args = args;
	}
}
