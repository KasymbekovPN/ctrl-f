package kpn.ctrlf.client.conversation.notifier;

public interface DomainChangeNotifier {
	boolean append(DomainChangeNotificationTask task);
	void dispose() throws InterruptedException;
}
