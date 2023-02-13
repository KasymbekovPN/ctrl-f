package kpn.ctrlf.client.conversation.response.factory;

import kpn.ctrlf.client.conversation.response.*;
import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
import kpn.ctrlf.client.conversation.response.value.Value;
import org.springframework.stereotype.Service;

@Service
public final class ResponseFactoryImpl implements ResponseFactory {
	@Override
	public Response createSimpleResponse(boolean success) {
		return new SimpleResponse(success);
	}

	@Override
	public Response createOkResponse(Value value) {
		return new OkResponse(value);
	}

	@Override
	public Response createErrorResponse(String code) {
		return new ErrorResponse(code);
	}

	@Override
	public Response createErrorArgResponse(String code, ErrorArgs args) {
		return new ErrorArgsResponse(code, args);
	}
}
