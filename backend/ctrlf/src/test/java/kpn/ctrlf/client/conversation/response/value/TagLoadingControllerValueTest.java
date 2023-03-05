package kpn.ctrlf.client.conversation.response.value;

import kpn.ctrlf.data.domain.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TagLoadingControllerValueTest {

	@Test
	void shouldCheckTagsGetting() {
		int size = 10;
		Tag[] expectedTags = new Tag[size];
		for (int i = 0; i < size; i++) {
			expectedTags[i] = new Tag((long)i, "name " + i);
		}

		Tag[] tags = new TagLoadingControllerValue(expectedTags).getTags();
		assertThat(tags).isEqualTo(expectedTags);
	}
}
