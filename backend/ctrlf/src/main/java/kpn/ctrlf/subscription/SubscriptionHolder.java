package kpn.ctrlf.subscription;

import java.util.Set;

public interface SubscriptionHolder<S> {
	boolean subscribe(S subscriber);
	boolean unsubscribe(S subscriber);
	Set<S> getSubscribers();
}
