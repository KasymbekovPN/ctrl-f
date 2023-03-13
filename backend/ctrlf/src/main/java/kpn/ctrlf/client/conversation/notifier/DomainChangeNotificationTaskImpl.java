package kpn.ctrlf.client.conversation.notifier;

import kpn.ctrlf.client.conversation.response.value.Value;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class DomainChangeNotificationTaskImpl implements DomainChangeNotificationTask {
	private final String destination;
	private final Value payload;
}
