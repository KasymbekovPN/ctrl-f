package kpn.ctrlf.client.conversation.response.converter;

import kpn.ctrlf.client.conversation.response.Response;
import kpn.ctrlf.client.conversation.response.converter.args.ErrorArgsConverter;
import kpn.ctrlf.client.conversation.response.converter.value.ValueConverter;
import kpn.lib.result.Result;

public interface ResponseConverter {
	Response convert(Result<?> input, ValueConverter valueConverter, ErrorArgsConverter errorArgsConverter);
}
