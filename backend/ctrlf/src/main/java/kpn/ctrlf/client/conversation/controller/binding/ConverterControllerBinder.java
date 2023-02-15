package kpn.ctrlf.client.conversation.controller.binding;

import kpn.ctrlf.client.conversation.controller.RequestController;
import kpn.ctrlf.client.conversation.response.converter.args.ErrorArgsConverter;
import kpn.ctrlf.client.conversation.response.converter.value.ValueConverter;
import kpn.ctrlf.eventDrive.publisher.Publisher;

public interface ConverterControllerBinder {
	void addController(RequestController<?> controller);
	void addValueConverter(ValueConverter converter);
	void addErrorArgsConverter(ErrorArgsConverter converter);
	void bind(Publisher publisher);
}
