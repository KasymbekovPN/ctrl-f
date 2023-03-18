package kpn.ctrlf.client.conversation.notifier;

import kpn.ctrlf.client.conversation.response.Response;

public interface DomainChangeNotificationTask {
	String getDestination();
	Response getPayload();
}
