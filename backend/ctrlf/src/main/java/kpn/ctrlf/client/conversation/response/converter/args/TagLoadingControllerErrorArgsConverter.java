package kpn.ctrlf.client.conversation.response.converter.args;

import kpn.ctrlf.client.conversation.controller.ControllerConverter;
import kpn.ctrlf.client.conversation.controller.Controllers;
import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
import kpn.ctrlf.client.conversation.response.args.TagLoadingControllerErrorArgs;
import org.springframework.stereotype.Component;

@ControllerConverter(Controllers.TAG_LOADING)
@Component
public final class TagLoadingControllerErrorArgsConverter implements ErrorArgsConverter{
	@Override
	public ErrorArgs convert(Object... input) {
		return new TagLoadingControllerErrorArgs(
			input.length > 0 ? String.valueOf(input[0]) : ""
		);
	}
}
