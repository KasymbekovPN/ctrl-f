package kpn.ctrlf.client.conversation.response.factory;

import kpn.ctrlf.client.conversation.controller.Controllers;
import kpn.ctrlf.client.conversation.response.converter.ResponseConverter;
import kpn.ctrlf.client.conversation.response.converter.args.ErrorArgsConverter;
import kpn.ctrlf.client.conversation.response.converter.value.ValueConverter;
import lombok.RequiredArgsConstructor;

import java.util.Map;


// TODO: 11.02.2023 del
@RequiredArgsConstructor
public final class ConverterFactoryImpl implements ConverterFactory {
	private final Map<Controllers, Converters> converters;

	@Override
	public ResponseConverter create(Controllers id) {
		return null;
	}

	@RequiredArgsConstructor
	public static class Converters {
		private final ValueConverter valueConverter;
		private final ErrorArgsConverter errorArgsConverter;
	}
}
