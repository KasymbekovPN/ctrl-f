package kpn.ctrlf.eventDrive.publisher;

import kpn.ctrlf.eventDrive.event.AfterStartEvent;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import static org.assertj.core.api.Assertions.assertThat;

class AfterStartEventPublisherTest {

	@Test
	void shouldCheckPublishing() {
		TestApplicationEventPublisher publisher = new TestApplicationEventPublisher();
		new AfterStartEventPublisher(publisher).publish();

		Object event = publisher.getEvent();
		assertThat(event.getClass()).isEqualTo(AfterStartEvent.class);
	}

	@Getter
	private static class TestApplicationEventPublisher implements ApplicationEventPublisher {
		private Object event;

		@Override
		public void publishEvent(Object event) {
			this.event = event;
		}
	}
}
