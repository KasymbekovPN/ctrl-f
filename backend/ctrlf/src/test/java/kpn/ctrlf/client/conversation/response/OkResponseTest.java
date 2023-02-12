package kpn.ctrlf.client.conversation.response;

import kpn.ctrlf.client.conversation.response.value.Value;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OkResponseTest {

	@Test
	void shouldCheckSuccessGetting() {
		OkResponse response = new OkResponse(new TestValue(0));

		assertThat(response.isSuccess()).isTrue();
	}

	@Test
	void shouldCheckValueGetting() {
		int expected = 123;
		OkResponse response = new OkResponse(new TestValue(expected));

		assertThat(response.getValue().getClass()).isEqualTo(TestValue.class);
		TestValue castValue = (TestValue) response.getValue();
		assertThat(castValue.getI()).isEqualTo(expected);
	}

	@RequiredArgsConstructor
	@Getter
	private static class TestValue implements Value {
		private final int i;
	}
}
