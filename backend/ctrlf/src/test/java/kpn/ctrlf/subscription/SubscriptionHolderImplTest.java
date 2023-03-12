package kpn.ctrlf.subscription;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SubscriptionHolderImplTest {

	private static final String SUBSCRIBER = "some.subscriber";

	@Test
	void shouldCheckSubscribersGetting_ifEmpty() {
		SubscriptionHolderImpl<String> holder = new SubscriptionHolderImpl<>();

		assertThat(holder.getSubscribers()).isEmpty();
	}

	@Test
	void shouldCheckSubscription_ifSubscriptionAbsent() {
		SubscriptionHolderImpl<String> holder = new SubscriptionHolderImpl<>();
		Set<String> subscribers = holder.getSubscribers();

		boolean result = holder.subscribe(SUBSCRIBER);
		assertThat(result).isTrue();
		assertThat(subscribers.size()).isEqualTo(1);
		assertThat(subscribers.contains(SUBSCRIBER)).isTrue();
	}

	@Test
	void shouldCheckSubscription_ifSubscriptionAlreadyExists() {
		SubscriptionHolderImpl<String> holder = new SubscriptionHolderImpl<>();
		Set<String> subscribers = holder.getSubscribers();

		holder.subscribe(SUBSCRIBER);
		boolean result = holder.subscribe(SUBSCRIBER);
		assertThat(result).isFalse();
		assertThat(subscribers.size()).isEqualTo(1);
		assertThat(subscribers.contains(SUBSCRIBER)).isTrue();
	}

	@Test
	void shouldCheckUnsubscription_ifSubscriptionAbsent() {
		SubscriptionHolderImpl<String> holder = new SubscriptionHolderImpl<>();
		Set<String> subscribers = holder.getSubscribers();

		boolean result = holder.unsubscribe(SUBSCRIBER);
		assertThat(result).isFalse();
		assertThat(subscribers).isEmpty();
	}

	@Test
	void shouldCheckUnsubscription_ifSubscriptionExists() {
		SubscriptionHolderImpl<String> holder = new SubscriptionHolderImpl<>();
		Set<String> subscribers = holder.getSubscribers();

		holder.subscribe(SUBSCRIBER);
		boolean result = holder.unsubscribe(SUBSCRIBER);
		assertThat(result).isTrue();
		assertThat(subscribers).isEmpty();
	}
}
