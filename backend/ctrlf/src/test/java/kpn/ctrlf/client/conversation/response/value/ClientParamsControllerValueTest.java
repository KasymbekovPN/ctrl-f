package kpn.ctrlf.client.conversation.response.value;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClientParamsControllerValueTest {

	@Test
	void shouldCheckLocaleGetting() {
		String expectedLocale = "some.locale";

		String locale = new ClientParamsControllerValue(expectedLocale).getLocale();
		assertThat(locale).isEqualTo(expectedLocale);
	}
}
