package kpn.ctrlf.client.conversation.response.converter.args;

import kpn.ctrlf.client.conversation.response.args.ErrorArgs;

public interface ErrorArgsConverter {
	ErrorArgs convert(Object... input);
}
