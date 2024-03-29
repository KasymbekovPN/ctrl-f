package kpn.ctrlf.client.conversation.response.converter.value;

import kpn.ctrlf.client.conversation.controller.ControllerConverter;
import kpn.ctrlf.client.conversation.controller.Controllers;
import kpn.ctrlf.client.conversation.response.value.TagLoadingControllerValue;
import kpn.ctrlf.client.conversation.response.value.Value;
import kpn.ctrlf.data.domain.Tag;
import org.springframework.stereotype.Component;

import java.util.List;

@ControllerConverter(Controllers.TAG_LOADING)
@Component
public final class TagLoadingControllerValueConverter implements ValueConverter{
	@Override
	public Value convert(Object input) {
		if (!(input instanceof List<?>)){
			return new TagLoadingControllerValue(new Tag[0]);
		}

		List<?> list = (List<?>) input;
		if (list.size() > 0 && list.get(0).getClass().equals(Tag.class)){
			List<Tag> tags = (List<Tag>) input;
			Tag[] array = new Tag[tags.size()];
			for (int i = 0; i < tags.size(); i++) {
				array[i] = tags.get(i);
			}
			return new TagLoadingControllerValue(array);
		} else {
			return new TagLoadingControllerValue(new Tag[0]);
		}
	}
}
