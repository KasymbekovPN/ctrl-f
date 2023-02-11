package kpn.ctrlf.client.conversation.response;

import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
import kpn.ctrlf.client.conversation.response.value.Value;

public interface Response {
	boolean isSuccess();
	Value getValue();
	String getCode();
	ErrorArgs getArgs();
}
