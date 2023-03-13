package kpn.ctrlf.client.conversation.notifier;

import kpn.ctrlf.client.conversation.response.value.Value;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DomainChangeNotificationTaskImplTest {
	private static final String DESTINATION = "some.destination";
	private static final Value PAYLOAD = new TestValue(123);

	@Test
	void shouldCheckDestinationGetting() {
		String destination = new DomainChangeNotificationTaskImpl(DESTINATION, PAYLOAD).getDestination();
		assertThat(destination).isEqualTo(DESTINATION);
	}

	@Test
	void shouldCheckPayloadGetting() {
		Value payload = new DomainChangeNotificationTaskImpl(DESTINATION, PAYLOAD).getPayload();
		assertThat(payload).isEqualTo(PAYLOAD);
	}

	@RequiredArgsConstructor
	@EqualsAndHashCode
	private static class TestValue implements Value {
		private final int i;
	}
}
