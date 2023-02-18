package kpn.ctrlf.bpp.binding;

import kpn.ctrlf.client.conversation.controller.RequestController;
import kpn.ctrlf.client.conversation.controller.binding.ConverterControllerHolder;
import kpn.ctrlf.client.conversation.response.converter.args.ErrorArgsConverter;
import kpn.ctrlf.client.conversation.response.converter.value.ValueConverter;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public final class ControllerConverterBindingBPP implements BeanPostProcessor {
	@Autowired
	@Setter
	private ConverterControllerHolder binder;

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (new IsRequestController().test(bean)){
			binder.addController((RequestController<?>) bean);
		} else if (new IsValueConverter().test(bean)){
			binder.addValueConverter((ValueConverter) bean);
		} else if (new IsErrorArgsConverter().test(bean)){
			binder.addErrorArgsConverter((ErrorArgsConverter) bean);
		}
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}

	public static class IsRequestController implements Predicate<Object> {
		@Override
		public boolean test(Object bean) {
			return Arrays.stream(
				bean.getClass().getInterfaces()).collect(Collectors.toSet()
			).contains(RequestController.class);
		}
	}

	public static class IsValueConverter implements Predicate<Object>{
		@Override
		public boolean test(Object bean) {
			return Arrays.stream(
				bean.getClass().getInterfaces()).collect(Collectors.toSet()
			).contains(ValueConverter.class);
		}
	}

	public static class IsErrorArgsConverter implements Predicate<Object> {
		@Override
		public boolean test(Object bean) {
			return Arrays.stream(
				bean.getClass().getInterfaces()).collect(Collectors.toSet()
			).contains(ErrorArgsConverter.class);
		}
	}
}
