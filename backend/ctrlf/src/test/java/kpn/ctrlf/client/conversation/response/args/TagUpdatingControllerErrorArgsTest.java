package kpn.ctrlf.client.conversation.response.args;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TagUpdatingControllerErrorArgsTest {
	private static final String ID = "123";
	private static final String NAME = "some.name";

	@Test
	void shouldCheckIdGetting() {
		String id = new TagUpdatingControllerErrorArgs(ID, NAME).getId();
		assertThat(id).isEqualTo(ID);
	}

	@Test
	void shouldCheckNameGetting() {
		String name = new TagUpdatingControllerErrorArgs(ID, NAME).getName();
		assertThat(name).isEqualTo(NAME);
	}
}
