package kpn.ctrlf.client.conversation.response.converter.args;

import kpn.ctrlf.client.conversation.controller.ArgsConverters;
import kpn.ctrlf.client.conversation.controller.ControllerConverter;
import kpn.ctrlf.client.conversation.controller.Controllers;
import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
import kpn.ctrlf.client.conversation.response.args.TagCreationControllerErrorArgs;
import org.springframework.stereotype.Component;

@ControllerConverter(Controllers.TAG_CREATION)
@Component(ArgsConverters.TAG_CREATION) // TODO: 18.02.2023 del value
public final class TagCreationControllerErrorArgsConverter implements ErrorArgsConverter {
	@Override
	public ErrorArgs convert(Object... input) {
		return new TagCreationControllerErrorArgs(
			input.length > 0 ? String.valueOf(input[0]) : ""
		);
	}
}
