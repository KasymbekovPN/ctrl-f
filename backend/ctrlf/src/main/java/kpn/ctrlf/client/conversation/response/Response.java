package kpn.ctrlf.client.conversation.response;

import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
import kpn.ctrlf.client.conversation.response.value.Value;

public interface Response {
	boolean isSuccess();
	default Value getValue() {return null;};
	default String getCode() {return null;};
	default ErrorArgs getArgs() {return null;};
}
