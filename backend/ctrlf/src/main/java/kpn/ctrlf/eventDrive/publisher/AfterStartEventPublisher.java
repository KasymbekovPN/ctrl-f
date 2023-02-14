package kpn.ctrlf.eventDrive.publisher;

import kpn.ctrlf.eventDrive.event.AfterStartEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class AfterStartEventPublisher {
	private final ApplicationEventPublisher publisher;

	public void publish(){
		publisher.publishEvent(new AfterStartEvent(this));
	}
}
