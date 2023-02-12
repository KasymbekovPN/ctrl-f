package kpn.ctrlf.client.conversation.controller;

// TODO: 12.02.2023 del
public interface RequestControllerOlf<T, R> {
	R response(String sessionId, T request);
}
