package kpn.ctrlf.client.conversation.controller.binding;

import kpn.ctrlf.client.conversation.controller.ControllerConverter;
import kpn.ctrlf.client.conversation.controller.Controllers;
import kpn.ctrlf.client.conversation.controller.RequestController;
import kpn.ctrlf.client.conversation.response.converter.args.ErrorArgsConverter;
import kpn.ctrlf.client.conversation.response.converter.value.ValueConverter;
import kpn.ctrlf.eventDrive.publisher.Publisher;
import org.springframework.stereotype.Service;

import java.util.EnumMap;

@Service
public class ConverterControllerBinderImpl implements ConverterControllerBinder {
	private final EnumMap<Controllers, RequestController<?>> controllers = new EnumMap<>(Controllers.class);
	private final EnumMap<Controllers, ValueConverter> valueConverters = new EnumMap<>(Controllers.class);
	private final EnumMap<Controllers, ValueConverter> errorArgsConverters = new EnumMap<>(Controllers.class);

	@Override
	public void addController(RequestController<?> controller) {
		ControllerConverter annotation = controller.getClass().getDeclaredAnnotation(ControllerConverter.class);
		if (annotation != null){
			controllers.put(annotation.value(), controller);
		}
	}

	@Override
	public void addValueConverter(ValueConverter converter) {

	}

	@Override
	public void addErrorArgsConverter(ErrorArgsConverter converter) {

	}

	@Override
	public void bind(Publisher publisher) {

	}
}
