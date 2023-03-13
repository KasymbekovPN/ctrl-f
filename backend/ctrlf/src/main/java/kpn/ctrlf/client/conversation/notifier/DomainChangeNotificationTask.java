package kpn.ctrlf.client.conversation.notifier;

import kpn.ctrlf.client.conversation.response.value.Value;

public interface DomainChangeNotificationTask {
	String getDestination();
	Value getPayload();
}
