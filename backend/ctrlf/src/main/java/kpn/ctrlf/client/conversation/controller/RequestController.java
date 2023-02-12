package kpn.ctrlf.client.conversation.controller;

import kpn.ctrlf.client.conversation.response.Response;

public interface RequestController<R> {
	Response response(String sessionId, R request);
}
