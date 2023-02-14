package kpn.ctrlf.eventDrive.event;

import org.springframework.context.ApplicationEvent;

public final class AfterStartEvent extends ApplicationEvent {
	public AfterStartEvent(Object source) {
		super(source);
	}
}
