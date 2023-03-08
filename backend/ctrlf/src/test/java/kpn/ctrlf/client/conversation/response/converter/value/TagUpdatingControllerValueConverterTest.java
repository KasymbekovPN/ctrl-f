package kpn.ctrlf.client.conversation.response.converter.value;

import kpn.ctrlf.client.conversation.response.value.TagUpdatingControllerValue;
import kpn.ctrlf.client.conversation.response.value.Value;
import kpn.ctrlf.data.domain.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TagUpdatingControllerValueConverterTest {
	private static final long DEFAULT_ID = 0L;
	private static final String DEFAULT_NAME = "unknown.name";

	@Test
	void shouldCheckConversion_ifInputNull() {
		Value value = new TagUpdatingControllerValueConverter().convert(null);

		assertThat(value.getClass()).isEqualTo(TagUpdatingControllerValue.class);
		TagUpdatingControllerValue castValue = (TagUpdatingControllerValue) value;
		assertThat(castValue.getId()).isEqualTo(DEFAULT_ID);
		assertThat(castValue.getName()).isEqualTo(DEFAULT_NAME);
	}

	@Test
	void shouldCheckConversion_ifInputHasWrongType() {
		Value value = new TagUpdatingControllerValueConverter().convert(new Object());

		assertThat(value.getClass()).isEqualTo(TagUpdatingControllerValue.class);
		TagUpdatingControllerValue castValue = (TagUpdatingControllerValue) value;
		assertThat(castValue.getId()).isEqualTo(DEFAULT_ID);
		assertThat(castValue.getName()).isEqualTo(DEFAULT_NAME);
	}

	@Test
	void shouldCheckConversion() {
		long expectedId = 123L;
		String expectedName = "some.name";
		Value value = new TagUpdatingControllerValueConverter().convert(new Tag(expectedId, expectedName));

		assertThat(value.getClass()).isEqualTo(TagUpdatingControllerValue.class);
		TagUpdatingControllerValue castValue = (TagUpdatingControllerValue) value;
		assertThat(castValue.getId()).isEqualTo(expectedId);
		assertThat(castValue.getName()).isEqualTo(expectedName);
	}
}
