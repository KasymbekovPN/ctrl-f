package kpn.ctrlf.client.conversation.response.converter;

import kpn.ctrlf.client.conversation.response.Response;
import kpn.ctrlf.client.conversation.response.converter.args.ErrorArgsConverter;
import kpn.ctrlf.client.conversation.response.converter.value.ValueConverter;
import kpn.ctrlf.client.conversation.response.factory.ResponseFactory;
import kpn.lib.result.Result;
import kpn.lib.seed.Seed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class ResponseConverterImpl implements ResponseConverter {
	private final ResponseFactory responseFactory;

	@Override
	public Response convert(Result<?> input, ValueConverter valueConverter, ErrorArgsConverter errorArgsConverter) {
		if (input.isSuccess()){
			return responseFactory.createOkResponse(valueConverter.convert(input.getValue()));
		} else {
			Seed seed = input.getSeed();
			String code = seed.getCode();
			Object[] args = seed.getArgs();
			return args.length > 0
				? responseFactory.createErrorArgResponse(code, errorArgsConverter.convert(args))
				: responseFactory.createErrorResponse(code);
		}
	}
}
