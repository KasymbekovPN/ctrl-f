package kpn.ctrlf.client.conversation.response.converter.value;

import kpn.ctrlf.client.conversation.controller.ControllerConverter;
import kpn.ctrlf.client.conversation.controller.Controllers;
import kpn.ctrlf.client.conversation.response.value.TagCreationControllerValue;
import kpn.ctrlf.client.conversation.response.value.Value;
import kpn.ctrlf.data.domain.Tag;
import org.springframework.stereotype.Component;

@ControllerConverter(Controllers.TAG_CREATION)
@Component
public final class TagCreationControllerValueConverter implements ValueConverter {
	@Override
	public Value convert(Object input) {
		Tag tag = (Tag) input;
		return new TagCreationControllerValue(tag.getId(), tag.getName());
	}
}
