package kpn.ctrlf.client.conversation.response.value;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TagCreationControllerValueTest {

	@Test
	void shouldCheckNameGetting() {
		String expectedName = "some.name";
		TagCreationControllerValue value = new TagCreationControllerValue(expectedName);

		assertThat(value.getName()).isEqualTo(expectedName);
	}
}
