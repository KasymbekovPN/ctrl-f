package kpn.ctrlf.config;

import kpn.ctrlf.subscription.SubscriptionHolder;
import kpn.ctrlf.subscription.SubscriptionHolderImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubscriptionHolderConfig {

	@Bean
	@Qualifier("tagSubscriptionHolder")
	public SubscriptionHolder<String> tagSubscriptionHolder(){
		return new SubscriptionHolderImpl<>();
	}
}
