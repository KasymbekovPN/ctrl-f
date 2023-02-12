package kpn.ctrlf.client.conversation.controller;

import kpn.ctrlf.client.conversation.request.EmptyRequest;
import kpn.ctrlf.client.conversation.response.OkResponse;
import kpn.ctrlf.client.conversation.response.factory.ResponseFactoryImpl;
import kpn.ctrlf.client.conversation.response.value.I18nControllerValue;
import kpn.ctrlf.client.i18n.I18nSource;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class I18nControllerTest {

	@Test
	void shouldCheckResponseMethod() {
		Map<String, Map<String, String>> init = Map.of(
			"en", Map.of("arg", "value")
		);
		Map<String, Map<String, String>> expected = Map.of(
			"arg", Map.of("en", "value")
		);

		I18nSource source = I18nSource.create(init);
		OkResponse response = (OkResponse) new I18nController(new ResponseFactoryImpl(), source).response("", new EmptyRequest());

		assertThat(response.isSuccess()).isTrue();
		assertThat(response.getValue().getClass()).isEqualTo(I18nControllerValue.class);
		I18nControllerValue castValue = (I18nControllerValue) response.getValue();
		assertThat(castValue.getTemplates()).isEqualTo(expected);
	}
}
