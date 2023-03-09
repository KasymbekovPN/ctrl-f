package kpn.ctrlf.client.conversation.response.converter.value;

import kpn.ctrlf.client.conversation.response.value.TagDeletingControllerValue;
import kpn.ctrlf.client.conversation.response.value.Value;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TagDeletingControllerValueConverterTest {

	@Test
	void shouldCheckConversion_ifInputNull() {
		Value value = new TagDeletingControllerValueConverter().convert(null);

		assertThat(value.getClass()).isEqualTo(TagDeletingControllerValue.class);
		TagDeletingControllerValue castValue = (TagDeletingControllerValue) value;
		assertThat(castValue.getId()).isNull();
	}

	@Test
	void shouldCheckConversion_ifInputHasWrongType() {
		Value value = new TagDeletingControllerValueConverter().convert("");

		assertThat(value.getClass()).isEqualTo(TagDeletingControllerValue.class);
		TagDeletingControllerValue castValue = (TagDeletingControllerValue) value;
		assertThat(castValue.getId()).isNull();
	}

	@Test
	void shouldCheckConversion_ifInputIsLong() {
		long expectedId = 123L;
		Value value = new TagDeletingControllerValueConverter().convert(expectedId);

		assertThat(value.getClass()).isEqualTo(TagDeletingControllerValue.class);
		TagDeletingControllerValue castValue = (TagDeletingControllerValue) value;
		assertThat(castValue.getId()).isEqualTo(expectedId);
	}
}
