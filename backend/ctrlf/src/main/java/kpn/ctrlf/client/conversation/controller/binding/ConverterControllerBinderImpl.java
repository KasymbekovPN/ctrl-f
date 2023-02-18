package kpn.ctrlf.client.conversation.controller.binding;

import kpn.ctrlf.client.conversation.controller.ControllerConverter;
import kpn.ctrlf.client.conversation.controller.Controllers;
import kpn.ctrlf.client.conversation.controller.RequestController;
import kpn.ctrlf.client.conversation.response.converter.args.ErrorArgsConverter;
import kpn.ctrlf.client.conversation.response.converter.value.ValueConverter;
import kpn.ctrlf.utils.delimiter.Delimiter;
import kpn.ctrlf.utils.delimiter.DelimiterImpl;
import kpn.lib.result.ImmutableResult;
import kpn.lib.result.Result;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

@Service
public class ConverterControllerBinderImpl implements ConverterControllerHolder, Binder {
	private static final Map<Integer, String> FAIL_INJECTION_TEMPLATES = new HashMap<>(){{
		put(1, "Controller %s: fail attempt of ValueConverter injection");
		put(2, "Controller %s: fail attempt of ErrorArgsConverter injection");
		put(3, "Controller %s: fail attempt of ValueConverter & ErrorArgsConverter injection");
	}};
	private static final Map<Integer, String> CONVERTER_ABSENCE_TEMPLATES = new HashMap<>(){{
		put(1, "Controller %s: ValueConverter is absent");
		put(2, "Controller %s: ErrorArgsConverter is absent");
		put(3, "Controller %s: ValueConverter & ErrorArgsConverter is absent");
	}};
	private static final Map<Integer, String> SEVERAL_CONVERTERS_TEMPLATES = new HashMap<>(){{
		put(1, "Controller %s: it has several ValueConverter fields");
		put(2, "Controller %s: it has several ErrorArgsConverter fields");
		put(3, "Controller %s: it has several ValueConverter & ErrorArgsConverter fields");
	}};

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
		DelimiterImpl delimiter = new DelimiterImpl("", " | ");
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

			int status = 0;
			if (valueConverterFields.size() == 1 && errorArgsConverterFields.size() == 1){
				if (valueConverters.containsKey(key) && errorArgsConverters.containsKey(key)){
					Field valueConverterField = valueConverterFields.get(0);
					valueConverterField.setAccessible(true);
					try {
						valueConverterField.set(controller, valueConverters.get(key));
					} catch (IllegalAccessException e) {
						status += 1;
					} finally {
						valueConverterField.setAccessible(false);
					}

					Field errorArgsConverterField = errorArgsConverterFields.get(0);
					errorArgsConverterField.setAccessible(true);
					try {
						errorArgsConverterField.set(controller, errorArgsConverters.get(key));
					} catch (IllegalAccessException e) {
						status += 2;
					} finally {
						errorArgsConverterField.setAccessible(false);
					}

					enrichCode(key, code, status, FAIL_INJECTION_TEMPLATES, delimiter);
				} else {
					status = (valueConverters.containsKey(key) ? 0 : 1) + (errorArgsConverters.containsKey(key) ? 0 : 2);
					enrichCode(key, code, status, CONVERTER_ABSENCE_TEMPLATES, delimiter);
				}
			} else {
				status = (valueConverterFields.size() > 1 ? 1 : 0) + (errorArgsConverterFields.size() > 1 ? 2 : 0);
				enrichCode(key, code, status, SEVERAL_CONVERTERS_TEMPLATES, delimiter);
			}
		}

		return code.isEmpty()
			? ImmutableResult.<Void>ok(null)
			: ImmutableResult.<Void>fail(code.toString());
	}

	private void enrichCode(Controllers key, StringBuilder code, int status, Map<Integer, String> templates, Delimiter delimiter) {
		if (templates.containsKey(status)){
			code
				.append(delimiter.next())
				.append(String.format(templates.get(status), key.name()));
		}
	}
}
