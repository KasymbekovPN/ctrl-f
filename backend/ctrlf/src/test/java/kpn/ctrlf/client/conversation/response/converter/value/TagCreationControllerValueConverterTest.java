package kpn.ctrlf.client.conversation.response.converter.value;

import kpn.ctrlf.client.conversation.response.value.TagCreationControllerValue;
import kpn.ctrlf.client.conversation.response.value.Value;
import kpn.ctrlf.data.domain.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TagCreationControllerValueConverterTest {

	@Test
	void shouldCheckConversion_ifInputNull() {
		Value value = new TagCreationControllerValueConverter().convert(null);

		assertThat(value.getClass()).isEqualTo(TagCreationControllerValue.class);
		TagCreationControllerValue castValue = (TagCreationControllerValue) value;
		assertThat(castValue.getName()).isEqualTo("unknown.name");
		assertThat(castValue.getId()).isEqualTo(0L);
	}

	@Test
	void shouldCheckConversion_ifInputHasWrongType() {
		Object input = new Object();
		Value value = new TagCreationControllerValueConverter().convert(input);

		assertThat(value.getClass()).isEqualTo(TagCreationControllerValue.class);
		TagCreationControllerValue castValue = (TagCreationControllerValue) value;
		assertThat(castValue.getName()).isEqualTo("unknown.name");
		assertThat(castValue.getId()).isEqualTo(0L);
	}

	@Test
	void shouldCheckConversion_ifInputHasRightType() {
		String expectedName = "some.name";
		long expectedId = 123L;
		Tag tag = new Tag(expectedId, expectedName);

		Value value = new TagCreationControllerValueConverter().convert(tag);

		assertThat(value.getClass()).isEqualTo(TagCreationControllerValue.class);
		TagCreationControllerValue castValue = (TagCreationControllerValue) value;
		assertThat(castValue.getName()).isEqualTo(expectedName);
		assertThat(castValue.getId()).isEqualTo(expectedId);
	}
}
