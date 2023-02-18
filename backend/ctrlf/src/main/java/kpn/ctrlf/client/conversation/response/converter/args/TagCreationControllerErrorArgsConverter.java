package kpn.ctrlf.client.conversation.response.converter.args;

import kpn.ctrlf.client.conversation.controller.ControllerConverter;
import kpn.ctrlf.client.conversation.controller.Controllers;
import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
import kpn.ctrlf.client.conversation.response.args.TagCreationControllerErrorArgs;
import org.springframework.stereotype.Component;

@ControllerConverter(Controllers.TAG_CREATION)
@Component
public final class TagCreationControllerErrorArgsConverter implements ErrorArgsConverter {
	@Override
	public ErrorArgs convert(Object... input) {
		return new TagCreationControllerErrorArgs(
			input.length > 0 ? String.valueOf(input[0]) : ""
		);
	}
}
