package kpn.ctrlf.config;

import kpn.ctrlf.client.conversation.notifier.DomainChangeNotifier;
import kpn.ctrlf.client.conversation.notifier.DomainChangeNotifierImpl;
import kpn.ctrlf.client.conversation.notifier.SenderImpl;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@Configuration
@ConfigurationProperties(prefix = "domain.change.notifier.queue")
public class DomainChangeNotifierConfig {
	private static final int DEFAULT_QUEUE_SIZE = 1_000;

	@Setter
	private int size;

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Bean
	public DomainChangeNotifier domainChangeNotifier() {
		ExecutorService taskProcessor = Executors.newSingleThreadExecutor(new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				Thread thread = new Thread(r);
				thread.setName("domain-change-notifier");
				return thread;
			}
		});
		return DomainChangeNotifierImpl.create(
			new ArrayBlockingQueue<>(calculateQueueSize()),
			taskProcessor,
			new SenderImpl(simpMessagingTemplate));
	}

	private int calculateQueueSize() {
		return size > 0 ? size : DEFAULT_QUEUE_SIZE;
	}
}
