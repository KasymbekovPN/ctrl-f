package kpn.ctrlf.eventDrive.listener;

import kpn.ctrlf.client.conversation.controller.binding.Binder;
import kpn.ctrlf.eventDrive.event.AfterStartEvent;
import kpn.lib.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public final class AfterStartEventListener {
	private final ApplicationContext context;
	private final Binder binder;

	@EventListener
	public void processEvent(AfterStartEvent event){
		Result<Void> result = binder.bind();
		if (!result.isSuccess()){
			log.error("{}", result.getSeed().getCode());
			((ConfigurableApplicationContext) context).close();
		}
	}
}
