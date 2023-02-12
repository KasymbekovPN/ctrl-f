package kpn.ctrlf.client.conversation.response.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
@Getter
public final class I18nControllerValue implements Value {
	private final Map<String, Map<String, String>> templates;
}
