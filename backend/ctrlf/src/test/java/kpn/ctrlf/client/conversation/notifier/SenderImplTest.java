package kpn.ctrlf.client.conversation.notifier;

import kpn.ctrlf.client.conversation.response.OkResponse;
import kpn.ctrlf.client.conversation.response.value.Value;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.core.MessagePostProcessor;
import org.springframework.messaging.core.MessageSendingOperations;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class SenderImplTest {

	@Test
	void shouldCheckSending() {
		TestMessageSendingOperations sendingOperations = new TestMessageSendingOperations();
		SenderImpl sender = new SenderImpl(sendingOperations);

		String expectedDestination = "some.destination";
		TestValue expectedValue = new TestValue("some.payload");

		DomainChangeNotificationTaskImpl task
			= new DomainChangeNotificationTaskImpl(expectedDestination, new OkResponse(expectedValue));
		sender.send(task);

		assertThat(sendingOperations.getDestination()).isEqualTo(expectedDestination);
		assertThat(sendingOperations.getPayload().getClass()).isEqualTo(OkResponse.class);
		OkResponse payload = (OkResponse) sendingOperations.getPayload();
		assertThat(payload.getValue()).isEqualTo(expectedValue);
	}

	@RequiredArgsConstructor
	@EqualsAndHashCode
	@Getter
	private static class TestValue implements Value {
		private final String value;
	}

	@Getter
	private static class TestMessageSendingOperations implements MessageSendingOperations<String>{
		private String destination;
		private Object payload;

		@Override
		public void convertAndSend(String destination, Object payload) throws MessagingException {
			this.destination = destination;
			this.payload = payload;
		}

		@Override
		public void send(Message<?> message) throws MessagingException {}
		@Override
		public void send(String destination, Message<?> message) throws MessagingException {}
		@Override
		public void convertAndSend(Object payload) throws MessagingException {}
		@Override
		public void convertAndSend(String destination, Object payload, Map<String, Object> headers) throws MessagingException {}
		@Override
		public void convertAndSend(Object payload, MessagePostProcessor postProcessor) throws MessagingException {}
		@Override
		public void convertAndSend(String destination, Object payload, MessagePostProcessor postProcessor) throws MessagingException {}
		@Override
		public void convertAndSend(String destination, Object payload, Map<String, Object> headers, MessagePostProcessor postProcessor) throws MessagingException {}
	}
}
