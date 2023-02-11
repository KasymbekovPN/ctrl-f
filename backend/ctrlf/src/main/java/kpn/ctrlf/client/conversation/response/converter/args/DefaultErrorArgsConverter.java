package kpn.ctrlf.client.conversation.response.converter.args;

import kpn.ctrlf.client.conversation.response.args.DefaultErrorArgs;
import kpn.ctrlf.client.conversation.response.args.ErrorArgs;

public final class DefaultErrorArgsConverter implements ErrorArgsConverter {
	@Override
	public ErrorArgs convert(Object... input) {
		return new DefaultErrorArgs();
	}
}
