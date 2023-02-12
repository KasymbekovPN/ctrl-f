package kpn.ctrlf.client.conversation.response.value;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class I18nControllerValueTest {

	@Test
	void shouldCheckContent() {
		Map<String, String> item = new HashMap<>() {{
			put("ru", "ruTr");
			put("en", "enTr");
		}};
		Map<String, Map<String, String>> map = new HashMap<>(){{
			put("code", item);
		}};
		I18nControllerValue value = new I18nControllerValue(map);

		assertThat(value.getTemplates()).isEqualTo(map);
	}
}
