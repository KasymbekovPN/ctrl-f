package kpn.ctrlf.client.conversation.response.args;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TagDeletingControllerErrorArgsTest {

	@Test
	void shouldCheckIdGetting() {
		String expectedId = "123";
		String id = new TagDeletingControllerErrorArgs(expectedId).getId();

		assertThat(id).isEqualTo(expectedId);
	}
}
