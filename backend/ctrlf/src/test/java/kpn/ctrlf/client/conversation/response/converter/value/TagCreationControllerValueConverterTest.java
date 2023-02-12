package kpn.ctrlf.client.conversation.response.converter.value;

import kpn.ctrlf.client.conversation.response.value.TagCreationControllerValue;
import kpn.ctrlf.client.conversation.response.value.Value;
import kpn.ctrlf.data.domain.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TagCreationControllerValueConverterTest {

	@Test
	void shouldCheckConversion() {
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
