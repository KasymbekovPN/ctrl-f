package kpn.ctrlf.client.conversation.response.value;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TagDeletingControllerValueTest {

	@Test
	void shouldCheckIdGetting() {
		long expectedId = 123L;
		Long id = new TagDeletingControllerValue(expectedId).getId();

		assertThat(id).isEqualTo(expectedId);
	}
}
