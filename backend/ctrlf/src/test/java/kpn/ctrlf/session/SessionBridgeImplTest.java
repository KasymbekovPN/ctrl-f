package kpn.ctrlf.session;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class SessionBridgeImplTest {

	private static final Map<String, String> SESSIONS = new HashMap<>() {{
		put("core-session-0", "gui-session-0");
		put("core-session-1", "gui-session-1");
		put("core-session-2", "gui-session-2");
	}};

	@Test
	void shouldCheckCorrespondenceGetting_ifItEmpty() {
		SessionBridgeImpl bridge = new SessionBridgeImpl();
		Map<String, String> correspondence = bridge.getCorrespondence();

		assertThat(correspondence).isEmpty();
	}

	@Test
	void shouldCheckCorrespondenceSettingGetting() {
		SessionBridgeImpl bridge = createAndEnrichSessionBridge();
		Map<String, String> correspondence = bridge.getCorrespondence();

		assertThat(correspondence).isEqualTo(SESSIONS);
	}

	@Test
	void shouldCheckCoreSessionGetting_ifItAbsent() {
		SessionBridgeImpl bridge = new SessionBridgeImpl();
		Optional<String> maybeCoreSession = bridge.getCoreSession("gui-session-0");

		assertThat(maybeCoreSession).isEmpty();
	}

	@Test
	void shouldCheckCoreSessionGetting_ifItPresent() {
		SessionBridgeImpl bridge = createAndEnrichSessionBridge();
		for (Map.Entry<String, String> entry : SESSIONS.entrySet()) {
			Optional<String> maybeCoreSession = bridge.getCoreSession(entry.getValue());
			assertThat(maybeCoreSession).isPresent();
			assertThat(maybeCoreSession.get()).isEqualTo(entry.getKey());
		}
	}

	@Test
	void shouldCheckGuiSessionGetting_ifItAbsent() {
		SessionBridgeImpl bridge = new SessionBridgeImpl();
		Optional<String> maybeGuiSession = bridge.getGuiSession("core-session-0");

		assertThat(maybeGuiSession).isEmpty();
	}

	@Test
	void shouldCheckGuiSessionGetting_ifItPresent() {
		SessionBridgeImpl bridge = createAndEnrichSessionBridge();
		for (Map.Entry<String, String> entry : SESSIONS.entrySet()) {
			Optional<String> maybeGuiSession = bridge.getGuiSession(entry.getKey());
			assertThat(maybeGuiSession).isPresent();
			assertThat(maybeGuiSession.get()).isEqualTo(entry.getValue());
		}
	}

	@Test
	void shouldCheckErasing() {
		SessionBridgeImpl bridge = createAndEnrichSessionBridge();
		assertThat(bridge.getCorrespondence()).isEqualTo(SESSIONS);

		for (Map.Entry<String, String> entry : SESSIONS.entrySet()) {
			bridge.eraseCorrespondence(entry.getKey());
		}
		assertThat(bridge.getCorrespondence()).isEmpty();
	}

	private static SessionBridgeImpl createAndEnrichSessionBridge(){
		SessionBridgeImpl bridge = new SessionBridgeImpl();
		for (Map.Entry<String, String> entry : SESSIONS.entrySet()) {
			bridge.setCorrespondence(entry.getKey(), entry.getValue());
		}

		return bridge;
	}
}
