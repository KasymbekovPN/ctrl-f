package kpn.ctrlf.client.conversation.response.converter.value;

import kpn.ctrlf.client.conversation.controller.ValueConverters;
import kpn.ctrlf.client.conversation.response.value.TagCreationControllerValue;
import kpn.ctrlf.client.conversation.response.value.Value;
import kpn.ctrlf.data.domain.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier(ValueConverters.TAG_CREATION)
public final class TagCreationControllerValueConverter implements ValueConverter {
	@Override
	public Value convert(Object input) {
		Tag tag = (Tag) input;
		return new TagCreationControllerValue(tag.getId(), tag.getName());
	}
}
