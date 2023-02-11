package kpn.ctrlf.client.conversation.response.converter.value;

import kpn.ctrlf.client.conversation.response.value.TagCreationControllerValue;
import kpn.ctrlf.client.conversation.response.value.Value;
import kpn.ctrlf.data.domain.Tag;

public final class TagCreationControllerValueConverter implements ValueConverter {
	@Override
	public Value convert(Object input) {
		Tag tag = (Tag) input;
		return new TagCreationControllerValue(tag.getName());
	}
}
