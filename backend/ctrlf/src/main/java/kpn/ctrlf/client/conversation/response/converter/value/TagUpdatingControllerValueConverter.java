package kpn.ctrlf.client.conversation.response.converter.value;

import kpn.ctrlf.client.conversation.controller.ControllerConverter;
import kpn.ctrlf.client.conversation.controller.Controllers;
import kpn.ctrlf.client.conversation.response.value.TagUpdatingControllerValue;
import kpn.ctrlf.client.conversation.response.value.Value;
import kpn.ctrlf.data.domain.Tag;
import org.springframework.stereotype.Component;

@ControllerConverter(Controllers.TAG_UPDATING)
@Component
public final class TagUpdatingControllerValueConverter implements ValueConverter{
	private static final long DEFAULT_ID = 0L;
	private static final String DEFAULT_NAME = "unknown.name";

	@Override
	public Value convert(Object input) {
		if (input == null){
			return new TagUpdatingControllerValue(DEFAULT_ID, DEFAULT_NAME);
		}
		if (input.getClass().equals(Tag.class)){
			Tag tag = (Tag) input;
			return new TagUpdatingControllerValue(tag.getId(), tag.getName());
		} else {
			return new TagUpdatingControllerValue(DEFAULT_ID, DEFAULT_NAME);
		}
	}
}
