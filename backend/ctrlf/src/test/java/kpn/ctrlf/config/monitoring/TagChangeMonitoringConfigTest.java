package kpn.ctrlf.config.monitoring;

import kpn.ctrlf.client.conversation.response.value.TagCreationControllerValue;
import kpn.ctrlf.client.conversation.response.value.TagDeletingControllerValue;
import kpn.ctrlf.client.conversation.response.value.TagUpdatingControllerValue;
import kpn.ctrlf.client.conversation.response.value.Value;
import kpn.ctrlf.data.domain.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TagChangeMonitoringConfigTest {
	private static final long ID = 123;
	private static final String NAME = "some.name";

	@Test
	void shouldCheckOnCreationConverter() {
		Value value = new TagChangeMonitoringConfig.OnCreationConverter()
			.apply(new Tag(ID, NAME));

		assertThat(value.getClass()).isEqualTo(TagCreationControllerValue.class);

		TagCreationControllerValue castValue = (TagCreationControllerValue) value;
		assertThat(castValue.getId()).isEqualTo(ID);
		assertThat(castValue.getName()).isEqualTo(NAME);
	}

	@Test
	void shouldCheckOnUpdatingConverter() {
		Value value = new TagChangeMonitoringConfig.OnUpdatingConverter()
			.apply(new Tag(ID, NAME));

		assertThat(value.getClass()).isEqualTo(TagUpdatingControllerValue.class);

		TagUpdatingControllerValue castValue = (TagUpdatingControllerValue) value;
		assertThat(castValue.getId()).isEqualTo(ID);
		assertThat(castValue.getName()).isEqualTo(NAME);
	}

	@Test
	void shouldCheckOnDeletingConverter() {
		Value value = new TagChangeMonitoringConfig.OnDeletingConverter()
			.apply(new Tag(ID, NAME));

		assertThat(value.getClass()).isEqualTo(TagDeletingControllerValue.class);

		TagDeletingControllerValue castValue = (TagDeletingControllerValue) value;
		assertThat(castValue.getId()).isEqualTo(ID);
	}
}
