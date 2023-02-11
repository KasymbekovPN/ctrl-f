package kpn.ctrlf.client.conversation.response.args;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TagCreationControllerErrorArgsTest {

	@Test
	void shouldNameGetting() {
		String expectedName = "some.name";
		TagCreationControllerErrorArgs args = new TagCreationControllerErrorArgs(expectedName);

		assertThat(args.getName()).isEqualTo(expectedName);
	}
}
