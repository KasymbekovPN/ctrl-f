package kpn.ctrlf.config.monitoring;

import kpn.ctrlf.client.conversation.notifier.DomainChangeNotificationTask;
import kpn.ctrlf.client.conversation.notifier.DomainChangeNotifier;
import kpn.ctrlf.data.domain.Tag;
import kpn.ctrlf.data.monitoring.ChangeMonitoringImpl;
import kpn.ctrlf.subscription.SubscriptionHolder;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "domain.monitoring.destination.tag")
public class TagChangeMonitoringConfig {
	@Setter
	private String creation;
	@Setter
	private String updating;
	@Setter
	private String deleting;

	@Autowired
	private DomainChangeNotifier domainChangeNotifier;
	@Autowired
	@Qualifier("tagSubscriptionHolder")
	private SubscriptionHolder<String> subscriptionHolder;


	@Bean
	public ChangeMonitoringImpl<Tag> tagChangeMonitoring(){
		return null;
//		return new ChangeMonitoringImpl<>(
//			domainChangeNotifier,
//			subscriptionHolder,
//			new TagOnCreationConverter(),
//
//		);
	}

//	@RequiredArgsConstructor
//	public static class TagOnCreationConverter implements ChangeMonitoringImpl.Converter<Tag>{
//		private final String destinationPrefix;
//
//		@Override
//		public DomainChangeNotificationTask convert(Tag domain, String subscriber) {
//			return null;
//		}
//	}
//
//	@RequiredArgsConstructor
//	public static class TagOnUpdatingConverter implements ChangeMonitoringImpl.Converter<Tag>{
//		private final String destinationPrefix;
//
//		@Override
//		public DomainChangeNotificationTask convert(Tag domain, String subscriber) {
//			return null;
//		}
//	}
//
//	@RequiredArgsConstructor
//	public static class TagOnDeletingConverter implements ChangeMonitoringImpl.Converter<Tag>{
//		private final String destinationPrefix;
//
//		@Override
//		public DomainChangeNotificationTask convert(Tag domain, String subscriber) {
//			return null;
//		}
//	}
}
