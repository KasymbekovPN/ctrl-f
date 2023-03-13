package kpn.ctrlf.client.conversation.notifier;

public interface DomainChangeNotifier {
	void sendNotification(DomainChangeNotificationTask task);
}
