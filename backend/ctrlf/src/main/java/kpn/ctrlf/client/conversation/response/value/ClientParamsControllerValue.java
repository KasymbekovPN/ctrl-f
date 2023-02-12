package kpn.ctrlf.client.conversation.response.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ClientParamsControllerValue implements Value{
	private final String locale;
}
