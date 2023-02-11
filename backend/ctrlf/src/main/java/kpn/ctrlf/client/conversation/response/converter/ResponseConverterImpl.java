package kpn.ctrlf.client.conversation.response.converter;

import kpn.ctrlf.client.conversation.response.Response;
import kpn.ctrlf.client.conversation.response.ResponseImpl;
import kpn.ctrlf.client.conversation.response.converter.args.ErrorArgsConverter;
import kpn.ctrlf.client.conversation.response.converter.value.ValueConverter;
import kpn.lib.result.Result;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ResponseConverterImpl implements ResponseConverter {
	private final ValueConverter valueConverter;
	private final ErrorArgsConverter errorArgsConverter;

	@Override
	public Response convert(Result<?> input) {
		return input.isSuccess()
			? new ResponseImpl(valueConverter.convert(input.getValue()))
			: new ResponseImpl(
				input.getSeed().getCode(),
				errorArgsConverter.convert(input.getSeed().getArgs())
			);
	}
}
