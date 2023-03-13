package kpn.ctrlf.client.conversation.notifier;

public interface Sender {
	void send(DomainChangeNotificationTask task);
}
