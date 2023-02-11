package kpn.ctrlf.client.conversation.response.converter.args;

import kpn.ctrlf.client.conversation.controller.ArgsConverters;
import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
import kpn.ctrlf.client.conversation.response.args.TagCreationControllerErrorArgs;
import org.springframework.stereotype.Component;

@Component(ArgsConverters.TAG_CREATION)
public final class TagCreationControllerErrorArgsConverter implements ErrorArgsConverter {
	@Override
	public ErrorArgs convert(Object... input) {
		return new TagCreationControllerErrorArgs(
			input.length > 0 ? String.valueOf(input[0]) : ""
		);
	}
}
