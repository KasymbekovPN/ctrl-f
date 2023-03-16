package kpn.ctrlf.data.monitoring;

import kpn.ctrlf.client.conversation.notifier.DomainChangeNotificationTask;
import kpn.ctrlf.client.conversation.notifier.DomainChangeNotificationTaskImpl;
import kpn.ctrlf.client.conversation.notifier.DomainChangeNotifier;
import kpn.ctrlf.client.conversation.response.value.Value;
import kpn.ctrlf.subscription.SubscriptionHolder;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@RequiredArgsConstructor
public class ChangeMonitoringImpl<D> implements ChangeMonitoring<D> {
	private final DomainChangeNotifier notifier;
	private final SubscriptionHolder<String> subscriptionHolder;
	private final Converter<D> onCreationConverter;
	private final Converter<D> onUpdatingConverter;
	private final Converter<D> onDeletingConverter;

	@Override
	public void traceCreation(D domain) {
		for (String subscriber : subscriptionHolder.getSubscribers()) {
			notifier.append(
				onCreationConverter.convert(domain, subscriber)
			);
		}
	}

	@Override
	public void traceUpdating(D domain) {
		for (String subscriber : subscriptionHolder.getSubscribers()) {
			notifier.append(
				onUpdatingConverter.convert(domain, subscriber)
			);
		}
	}

	@Override
	public void traceDeleting(D domain) {
		for (String subscriber : subscriptionHolder.getSubscribers()) {
			notifier.append(
				onDeletingConverter.convert(domain, subscriber)
			);
		}
	}

	public interface Converter<D> {
		DomainChangeNotificationTask convert(D domain, String subscriber);
	}

	@RequiredArgsConstructor
	public static class BaseConverter<D> implements Converter<D> {
		private final Function<D, Value> domainConverter;
		private final String destinationPrefix;

		@Override
		public DomainChangeNotificationTask convert(D domain, String subscriber) {
			return new DomainChangeNotificationTaskImpl(
				destinationPrefix + subscriber,
				domainConverter.apply(domain)
			);
		}
	}
}
