package kpn.ctrlf.client.conversation.response.converter.args;

import kpn.ctrlf.client.conversation.controller.ControllerConverter;
import kpn.ctrlf.client.conversation.controller.Controllers;
import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
import kpn.ctrlf.client.conversation.response.args.TagUpdatingControllerErrorArgs;
import org.springframework.stereotype.Component;

@ControllerConverter(Controllers.TAG_UPDATING)
@Component
public final class TagUpdatingControllerErrorArgsConverter implements ErrorArgsConverter{
	@Override
	public ErrorArgs convert(Object... input) {
		return new TagUpdatingControllerErrorArgs(
			input.length > 1 ? String.valueOf(input[1]) : "",
			input.length > 0 ? String.valueOf(input[0]) : ""
		);
	}
}
