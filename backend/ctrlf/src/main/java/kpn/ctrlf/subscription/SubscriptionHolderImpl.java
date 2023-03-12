package kpn.ctrlf.subscription;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class SubscriptionHolderImpl<S> implements SubscriptionHolder<S> {
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	private final Lock readLock = lock.readLock();
	private final Lock writeLock = lock.writeLock();

	private final Set<S> subscribers = new HashSet<>();

	@Override
	public boolean subscribe(S subscriber) {
		writeLock.lock();
			boolean result = subscribers.add(subscriber);
		writeLock.unlock();
		return result;
	}

	@Override
	public boolean unsubscribe(S subscriber) {
		writeLock.lock();
			boolean result = subscribers.remove(subscriber);
		writeLock.unlock();
		return result;
	}

	@Override
	public Set<S> getSubscribers() {
		readLock.lock();
			Set<S> result = Collections.unmodifiableSet(subscribers);
		readLock.unlock();
		return result;
	}
}
