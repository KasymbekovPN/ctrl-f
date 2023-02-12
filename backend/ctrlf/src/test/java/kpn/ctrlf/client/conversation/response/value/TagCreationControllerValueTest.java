package kpn.ctrlf.client.conversation.response.value;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TagCreationControllerValueTest {

	private static final Long EXPECTED_ID = 123L;
	private static final String EXPECTED_NAME = "some.name";

	@Test
	void shouldCheckIdGetting() {
		TagCreationControllerValue value = new TagCreationControllerValue(EXPECTED_ID, EXPECTED_NAME);

		assertThat(value.getId()).isEqualTo(EXPECTED_ID);
	}

	@Test
	void shouldCheckNameGetting() {
		TagCreationControllerValue value = new TagCreationControllerValue(EXPECTED_ID, EXPECTED_NAME);

		assertThat(value.getName()).isEqualTo(EXPECTED_NAME);
	}
}
