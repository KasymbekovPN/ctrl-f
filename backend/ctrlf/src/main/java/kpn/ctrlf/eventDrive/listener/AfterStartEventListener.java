package kpn.ctrlf.eventDrive.listener;

import kpn.ctrlf.eventDrive.event.AfterStartEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class AfterStartEventListener {
	private final ApplicationContext context;

	@EventListener
	public void processEvent(AfterStartEvent event){
		// TODO: 14.02.2023 add bean-checker for ArgsConverters
		// TODO: 14.02.2023 add bean-checker for ValuesConverters

		System.out.println("---- processEvent");
//		((ConfigurableApplicationContext) context).close();
	}
}
