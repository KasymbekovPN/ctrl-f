package kpn.ctrlf.client.conversation.controller.binding;

import kpn.ctrlf.client.conversation.controller.RequestController;
import kpn.ctrlf.client.conversation.response.converter.args.ErrorArgsConverter;
import kpn.ctrlf.client.conversation.response.converter.value.ValueConverter;
import kpn.lib.result.Result;

// TODO: 18.02.2023 separate interface 3 / bind
public interface ConverterControllerBinder {
	void addController(RequestController<?> controller);
	void addValueConverter(ValueConverter converter);
	void addErrorArgsConverter(ErrorArgsConverter converter);
	Result<Void> bind();
}
