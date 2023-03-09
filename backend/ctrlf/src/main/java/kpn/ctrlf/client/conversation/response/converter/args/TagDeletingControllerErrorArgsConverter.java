package kpn.ctrlf.client.conversation.response.converter.args;

import kpn.ctrlf.client.conversation.controller.ControllerConverter;
import kpn.ctrlf.client.conversation.controller.Controllers;
import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
import kpn.ctrlf.client.conversation.response.args.TagDeletingControllerErrorArgs;
import org.springframework.stereotype.Component;

@ControllerConverter(Controllers.TAG_DELETING)
@Component
public final class TagDeletingControllerErrorArgsConverter implements ErrorArgsConverter{
	private static final String DEFAULT_ID = "?";

	@Override
	public ErrorArgs convert(Object... input) {
		return new TagDeletingControllerErrorArgs(
			input.length == 0 ? DEFAULT_ID : String.valueOf(input[0])
		);
	}
}
