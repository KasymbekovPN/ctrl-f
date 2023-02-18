package kpn.ctrlf.client.conversation.response.args;

import kpn.ctrlf.client.conversation.controller.ControllerConverter;
import kpn.ctrlf.client.conversation.controller.Controllers;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class TagCreationControllerErrorArgs implements ErrorArgs {
	private final String name;
}
