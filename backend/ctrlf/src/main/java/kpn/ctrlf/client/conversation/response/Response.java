package kpn.ctrlf.client.conversation.response;

import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
import kpn.ctrlf.client.conversation.response.value.Value;

public interface Response<V extends Value, E extends ErrorArgs> {
	boolean isSuccess();
	V getValue();
	String getCode();
	ErrorArgs getArgs();
}
