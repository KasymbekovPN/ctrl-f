package kpn.ctrlf.client.conversation.response.converter.value;

import kpn.ctrlf.client.conversation.response.value.TagLoadingControllerValue;
import kpn.ctrlf.client.conversation.response.value.Value;
import kpn.ctrlf.data.domain.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class TagLoadingControllerConverterTest {

	@Test
	void shouldCheckConversion_ifInputNull() {
		Value value = new TagLoadingControllerConverter().convert(null);
		assertThat(value.getClass()).isEqualTo(TagLoadingControllerValue.class);
		TagLoadingControllerValue castValue = (TagLoadingControllerValue) value;

		assertThat(castValue.getTags()).isEmpty();
	}

	@Test
	void shouldCheckConversion_ifInputHasWrongType() {
		Object input = new Object();
		Value value = new TagLoadingControllerConverter().convert(input);
		assertThat(value.getClass()).isEqualTo(TagLoadingControllerValue.class);
		TagLoadingControllerValue castValue = (TagLoadingControllerValue) value;

		assertThat(castValue.getTags()).isEmpty();
	}

	@Test
	void shouldCheckConversion_ifInputIsEmptyList() {
		Value value = new TagLoadingControllerConverter().convert(List.of());
		assertThat(value.getClass()).isEqualTo(TagLoadingControllerValue.class);
		TagLoadingControllerValue castValue = (TagLoadingControllerValue) value;

		assertThat(castValue.getTags()).isEmpty();
	}

	@Test
	void shouldCheckConversion_ifInputIsListWithWrongType() {
		Value value = new TagLoadingControllerConverter().convert(List.of(0));
		assertThat(value.getClass()).isEqualTo(TagLoadingControllerValue.class);
		TagLoadingControllerValue castValue = (TagLoadingControllerValue) value;

		assertThat(castValue.getTags()).isEmpty();
	}

	@Test
	void shouldCheckConversion_ifInputIsListWithRightType() {
		int size = 10;
		Tag[] tags = new Tag[size];
		for (int i = 0; i < size; i++) {
			tags[i] = new Tag((long)i, "name " + i);
		}

		Value value = new TagLoadingControllerConverter().convert(Arrays.stream(tags).collect(Collectors.toList()));
		assertThat(value.getClass()).isEqualTo(TagLoadingControllerValue.class);
		TagLoadingControllerValue castValue = (TagLoadingControllerValue) value;

		assertThat(castValue.getTags()).isEqualTo(tags);
	}
}
