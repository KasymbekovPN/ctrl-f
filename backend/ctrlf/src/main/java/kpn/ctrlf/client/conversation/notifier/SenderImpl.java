package kpn.ctrlf.client.conversation.notifier;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.core.MessageSendingOperations;

@RequiredArgsConstructor
public final class SenderImpl implements Sender {
	private final MessageSendingOperations<String> sendingOperations;

	@Override
	public void send(DomainChangeNotificationTask task) {
		sendingOperations.convertAndSend(task.getDestination(), task.getPayload());
	}
}
