package kpn.ctrlf.session;

import java.util.Map;
import java.util.Optional;

public interface SessionBridge {
	void setCorrespondence(String coreSession, String guiSession);
	Optional<String> getCoreSession(String guiSession);
	Optional<String> getGuiSession(String coreSession);
	Map<String, String> getCorrespondence();
	void eraseCorrespondence(String coreSession);
}
