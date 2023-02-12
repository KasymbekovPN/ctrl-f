package kpn.ctrlf.client.conversation.response.factory;

import kpn.ctrlf.client.conversation.response.Response;
import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
import kpn.ctrlf.client.conversation.response.value.Value;

public interface ResponseFactory {
	Response createSimpleResponse(boolean success);
	Response createOkResponse(Value value);
	Response createErrorResponse(String code);
	Response createErrorArgResponse(String code, ErrorArgs args);
}
