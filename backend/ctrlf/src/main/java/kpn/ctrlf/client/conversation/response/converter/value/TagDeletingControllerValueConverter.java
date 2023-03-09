package kpn.ctrlf.client.conversation.response.converter.value;

import kpn.ctrlf.client.conversation.controller.ControllerConverter;
import kpn.ctrlf.client.conversation.controller.Controllers;
import kpn.ctrlf.client.conversation.response.value.TagDeletingControllerValue;
import kpn.ctrlf.client.conversation.response.value.Value;
import org.springframework.stereotype.Component;

@ControllerConverter(Controllers.TAG_DELETING)
@Component
public final class TagDeletingControllerValueConverter implements ValueConverter{

	@Override
	public Value convert(Object input) {
		return new TagDeletingControllerValue(
			input != null && input.getClass().equals(Long.class)
				? (Long) input
				: null
		);
	}
}
