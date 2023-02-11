package kpn.ctrlf.client.conversation.response.converter.value;

import kpn.ctrlf.client.conversation.response.value.Value;

public interface ValueConverter {
	Value convert(Object input);
}
