package kpn.ctrlf.session;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public final class SessionBridgeImpl implements SessionBridge {
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	private final Lock readLock = lock.readLock();
	private final Lock writeLock = lock.writeLock();

	private final Map<String, String> correspondence = new HashMap<>();

	@Override
	public void setCorrespondence(String coreSession, String guiSession) {
		writeLock.lock();
			correspondence.put(coreSession, guiSession);
		writeLock.unlock();
	}

	@Override
	public Optional<String> getCoreSession(String guiSession) {
		Optional<String> result = Optional.empty();
		readLock.lock();
			if (correspondence.containsValue(guiSession)){
				for (Map.Entry<String, String> entry : correspondence.entrySet()) {
					if (entry.getValue().equals(guiSession)){
						result = Optional.of(entry.getKey());
						break;
					}
				}
			}
		readLock.unlock();

		return result;
	}

	@Override
	public Optional<String> getGuiSession(String coreSession) {
		Optional<String> result = Optional.empty();
		readLock.lock();
			if (correspondence.containsKey(coreSession)){
				result = Optional.of(correspondence.get(coreSession));
			}
		readLock.unlock();

		return result;
	}

	@Override
	public Map<String, String> getCorrespondence() {
		readLock.lock();
			Map<String, String> map = Collections.unmodifiableMap(correspondence);
		readLock.unlock();
		return map;
	}

	@Override
	public void eraseCorrespondence(String coreSession) {
		writeLock.lock();
			correspondence.remove(coreSession);
		writeLock.unlock();
	}
}
