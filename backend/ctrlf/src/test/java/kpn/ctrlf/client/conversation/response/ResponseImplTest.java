// TODO: 12.02.2023 del
//package kpn.ctrlf.client.conversation.response;
//
//import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
//import kpn.ctrlf.client.conversation.response.value.Value;
//import lombok.EqualsAndHashCode;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//class ResponseImplTest {
//
//	@Test
//	void shouldCheckSuccessResponse() {
//		TestValue expectedValue = new TestValue(0);
//		ResponseImpl response = new ResponseImpl(expectedValue);
//
//		assertThat(response.isSuccess()).isTrue();
//		assertThat(response.getValue()).isEqualTo(expectedValue);
//		assertThat(response.getCode()).isNull();
//		assertThat(response.getArgs()).isNull();
//	}
//
//	@Test
//	void shouldCheckFailResponse() {
//		String expectedCode = "some.code";
//		TestErrorArgs expectedArgs = new TestErrorArgs(0);
//		ResponseImpl response = new ResponseImpl(expectedCode, expectedArgs);
//
//		assertThat(response.isSuccess()).isFalse();
//		assertThat(response.getValue()).isNull();
//		assertThat(response.getCode()).isEqualTo(expectedCode);
//		assertThat(response.getArgs()).isEqualTo(expectedArgs);
//	}
//
//	@RequiredArgsConstructor
//	@EqualsAndHashCode
//	private static class TestValue implements Value {
//		private final int i;
//	}
//
//	@RequiredArgsConstructor
//	@EqualsAndHashCode
//	private static class TestErrorArgs implements ErrorArgs {
//		private final int i;
//	}
//}
