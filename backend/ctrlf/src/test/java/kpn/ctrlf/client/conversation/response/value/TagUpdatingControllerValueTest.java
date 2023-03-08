package kpn.ctrlf.client.conversation.response.value;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TagUpdatingControllerValueTest {
	private static final long ID = 123L;
	private static final String NAME = "some.name";

	@Test
	void shouldCheckIdGetting() {
		Long id = new TagUpdatingControllerValue(ID, NAME).getId();

		assertThat(id).isEqualTo(ID);
	}

	@Test
	void shouldCheckNameGetting() {
		String name = new TagUpdatingControllerValue(ID, NAME).getName();

		assertThat(name).isEqualTo(NAME);
	}
}
