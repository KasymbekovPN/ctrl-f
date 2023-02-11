package kpn.ctrlf.client.conversation.response.converter;

import kpn.ctrlf.client.conversation.response.Response;
import kpn.lib.result.Result;

public interface ResponseConverter {
	Response convert(Result<?> input);
}
