package kpn.ctrlf.config;

import kpn.ctrlf.session.SessionBridgeImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

class WSConfigTest {

	private static final String CORE_SESSION_ID_KEY = "simpSessionId";
	private static final String CORE_SESSION_ID_VALUE = "some.core.session.id";
	private static final String GUI_SESSION_ID_KEY =  "simpDestination";
	private static final String GUI_SESSION_ID = "some.gui.session.id";
	private static final String GUI_SESSION_ID_VALUE = "/topic/some.response/" + GUI_SESSION_ID;


	@Test
	void shouldCheckCoreSessionIdExtractor_ifHeadersEmpty() {
		MessageHeaders headers = new MessageHeaders(new HashMap<>());
		Optional<String> maybeSessionId = new WSConfig.CoreSessionIdExtractor().apply(headers);

		assertThat(maybeSessionId).isEmpty();
	}

	@Test
	void shouldCheckCoreSessionIdExtractor() {
		HashMap<String, Object> map = new HashMap<>(){{
			put(CORE_SESSION_ID_KEY, CORE_SESSION_ID_VALUE);
		}};
		MessageHeaders headers = new MessageHeaders(map);
		Optional<String> maybeSessionId = new WSConfig.CoreSessionIdExtractor().apply(headers);

		assertThat(maybeSessionId).isPresent();
		assertThat(maybeSessionId.get()).isEqualTo(CORE_SESSION_ID_VALUE);
	}

	@Test
	void shouldCheckGuiSessionIdExtractor_ifHeadersEmpty() {
		MessageHeaders headers = new MessageHeaders(new HashMap<>());
		Optional<String> maybeSessionId = new WSConfig.GuiSessionIdExtractor().apply(headers);

		assertThat(maybeSessionId).isEmpty();
	}

	@Test
	void shouldCheckGuiSessionIdExtractor() {
		HashMap<String, Object> map = new HashMap<>(){{
			put(GUI_SESSION_ID_KEY, GUI_SESSION_ID_VALUE);
		}};

		MessageHeaders headers = new MessageHeaders(map);
		Optional<String> maybeSessionId = new WSConfig.GuiSessionIdExtractor().apply(headers);

		assertThat(maybeSessionId).isPresent();
		assertThat(maybeSessionId.get()).isEqualTo(GUI_SESSION_ID);
	}

	@ParameterizedTest
	@CsvFileSource(resources = "shouldCheckInterceptor.csv")
	void shouldCheckInterceptor(String expectedCodeSessionId, String expectedGuiSessionId) {
		SessionBridgeImpl bridge = new SessionBridgeImpl();
		WSConfig.SessionIdExecutorChannelInterceptor interceptor = new WSConfig.SessionIdExecutorChannelInterceptor(
			bridge,
			new TestExtractor(expectedCodeSessionId),
			new TestExtractor(expectedGuiSessionId)
		);

		interceptor.beforeHandle(new GenericMessage<>(new Object(), new HashMap<>()), null, null);

		if (expectedCodeSessionId != null && expectedGuiSessionId != null){
			assertThat(bridge.getGuiSession(expectedCodeSessionId)).isPresent();
			assertThat(bridge.getGuiSession(expectedCodeSessionId).get()).isEqualTo(expectedGuiSessionId);
		} else {
			assertThat(bridge.getCorrespondence()).isEmpty();
		}
	}

	@RequiredArgsConstructor
	private static class TestExtractor implements Function<MessageHeaders, Optional<String>>{
		private final String id;

		@Override
		public Optional<String> apply(MessageHeaders messageHeaders) {
			return id != null ? Optional.of(id) : Optional.empty();
		}
	}
}
