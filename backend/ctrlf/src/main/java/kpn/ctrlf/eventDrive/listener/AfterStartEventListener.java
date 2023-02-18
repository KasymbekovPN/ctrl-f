package kpn.ctrlf.eventDrive.listener;

import kpn.ctrlf.client.conversation.controller.binding.ConverterControllerBinder;
import kpn.ctrlf.eventDrive.event.AfterStartEvent;
import kpn.lib.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class AfterStartEventListener {
	private final ApplicationContext context;
	private final ConverterControllerBinder binder;

	@EventListener
	public void processEvent(AfterStartEvent event){
		Result<Void> result = binder.bind();
		if (!result.isSuccess()){
			((ConfigurableApplicationContext) context).close();
		}
	}
}
