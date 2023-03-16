package kpn.ctrlf.config.monitoring;

import kpn.ctrlf.client.conversation.notifier.DomainChangeNotifier;
import kpn.ctrlf.client.conversation.response.value.TagCreationControllerValue;
import kpn.ctrlf.client.conversation.response.value.TagDeletingControllerValue;
import kpn.ctrlf.client.conversation.response.value.TagUpdatingControllerValue;
import kpn.ctrlf.client.conversation.response.value.Value;
import kpn.ctrlf.data.domain.Tag;
import kpn.ctrlf.data.monitoring.ChangeMonitoringImpl;
import kpn.ctrlf.subscription.SubscriptionHolder;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

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
		return new ChangeMonitoringImpl<>(
			domainChangeNotifier,
			subscriptionHolder,
			new ChangeMonitoringImpl.BaseConverter<>(new OnCreationConverter(), creation),
			new ChangeMonitoringImpl.BaseConverter<>(new OnUpdatingConverter(), updating),
			new ChangeMonitoringImpl.BaseConverter<>(new OnDeletingConverter(), deleting)
		);
	}

	public static final class OnCreationConverter implements Function<Tag, Value>{
		@Override
		public Value apply(Tag tag) {
			return new TagCreationControllerValue(tag.getId(), tag.getName());
		}
	}

	public static final class OnUpdatingConverter implements Function<Tag, Value> {
		@Override
		public Value apply(Tag tag) {
			return new TagUpdatingControllerValue(tag.getId(), tag.getName());
		}
	}

	public static final class OnDeletingConverter implements Function<Tag, Value> {
		@Override
		public Value apply(Tag tag) {
			return new TagDeletingControllerValue(tag.getId());
		}
	}
}
