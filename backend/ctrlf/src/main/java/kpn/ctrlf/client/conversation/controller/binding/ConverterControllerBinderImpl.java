package kpn.ctrlf.client.conversation.controller.binding;

import kpn.ctrlf.client.conversation.controller.ControllerConverter;
import kpn.ctrlf.client.conversation.controller.Controllers;
import kpn.ctrlf.client.conversation.controller.RequestController;
import kpn.ctrlf.client.conversation.controller.ValueConverters;
import kpn.ctrlf.client.conversation.response.converter.args.ErrorArgsConverter;
import kpn.ctrlf.client.conversation.response.converter.value.ValueConverter;
import kpn.lib.result.ImmutableResult;
import kpn.lib.result.Result;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

@Service
public class ConverterControllerBinderImpl implements ConverterControllerBinder {
	private final EnumMap<Controllers, RequestController<?>> controllers = new EnumMap<>(Controllers.class);
	private final EnumMap<Controllers, ValueConverter> valueConverters = new EnumMap<>(Controllers.class);
	private final EnumMap<Controllers, ErrorArgsConverter> errorArgsConverters = new EnumMap<>(Controllers.class);

	@Override
	public void addController(RequestController<?> controller) {
		ControllerConverter annotation = controller.getClass().getDeclaredAnnotation(ControllerConverter.class);
		if (annotation != null){
			controllers.put(annotation.value(), controller);
		}
	}

	@Override
	public void addValueConverter(ValueConverter converter) {
		ControllerConverter annotation = converter.getClass().getDeclaredAnnotation(ControllerConverter.class);
		if (annotation != null){
			valueConverters.put(annotation.value(), converter);
		}
	}

	@Override
	public void addErrorArgsConverter(ErrorArgsConverter converter) {
		ControllerConverter annotation = converter.getClass().getDeclaredAnnotation(ControllerConverter.class);
		if (annotation != null){
			errorArgsConverters.put(annotation.value(), converter);
		}
	}

	@Override
	public Result<Void> bind() {
		String delimiter = ""; // TODO: 16.02.2023 make class Delimiter with overrided toString : first time returns one, then second strings
		StringBuilder code = new StringBuilder();
		for (Map.Entry<Controllers, RequestController<?>> entry : controllers.entrySet()) {
			Controllers key = entry.getKey();
			RequestController<?> controller = entry.getValue();

			ArrayList<Field> valueConverterFields = new ArrayList<>();
			ArrayList<Field> errorArgsConverterFields = new ArrayList<>();
			Field[] fields = controller.getClass().getDeclaredFields();
			for (Field field : fields) {
				Class<?> type = field.getType();
				if (type.equals(ValueConverter.class)){
					valueConverterFields.add(field);
				} else if (type.equals(ErrorArgsConverter.class)){
					errorArgsConverterFields.add(field);
				}
			}

			if (valueConverterFields.size() == 1 && errorArgsConverterFields.size() == 1){
				if (valueConverters.containsKey(key) && errorArgsConverters.containsKey(key)){
					// TODO: 16.02.2023 impl
				} else {
					int status = 0;
					if (!valueConverters.containsKey(key)) {
						status += 1;
					}
					if (!errorArgsConverters.containsKey(key)) {
						status += 2;
					}
					switch (status){
						case 1:
							code.append(delimiter).append("Controller ").append(key.name()).append(": value converter is absent");
							delimiter = " | ";
							break;
						case 2:
							code.append(delimiter).append("Controller ").append(key.name()).append(": error converter is absent");
							delimiter = " | ";
							break;
						case 3:
							code.append(delimiter).append("Controller ").append(key.name()).append(": value & error converters is absent");
							delimiter = " | ";
							break;
					}
				}
			} else {

				int status = 0;
				if (valueConverterFields.size() > 1){
					status += 1;
				}
				if (errorArgsConverterFields.size() > 1){
					status += 2;
				}

				switch (status){
					case 1:
						code.append(delimiter).append("Controller ").append(key.name()).append(": it has several ValueConverter fields");
						delimiter = " | ";
						break;
					case 2:
						code.append(delimiter).append("Controller ").append(key.name()).append(": it has several ErrorArgsConverter fields");
						delimiter = " | ";
						break;
					case 3:
						code.append(delimiter).append("Controller ").append(key.name()).append(": it has several ValueConverter & ErrorArgsConverter fields");
						delimiter = " | ";
						break;
				}
			}

			//<
			System.out.println("");
		}

		return ImmutableResult.<Void>fail(code.toString());
	}
}
