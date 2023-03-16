package kpn.ctrlf.data.changing;

import kpn.ctrlf.data.monitoring.ChangeMonitoringImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BaseDoerTest {

	@Test
	void shouldCheckSessionSetting() {
		String expected = "some.session";
		Doer<String> result = new TestBaseDoer().session(expected);

		assertThat(result.getClass()).isEqualTo(TestBaseDoer.class);

		TestBaseDoer castResult = (TestBaseDoer) result;
		assertThat(castResult.getSession()).isEqualTo(expected);
	}

	@Test
	void shouldCheckValueSetting() {
		String expected = "some.value";
		Doer<String> result = new TestBaseDoer().value(expected);

		assertThat(result.getClass()).isEqualTo(TestBaseDoer.class);

		TestBaseDoer castResult = (TestBaseDoer) result;
		assertThat(castResult.getValue()).isEqualTo(expected);
	}

	private static class TestBaseDoer extends BaseDoer<String>{

		public String getSession(){
			return session;
		}

		public String getValue() {
			return value;
		}

		@Override
		public ChangeMonitoringImpl<String> apply() {return null;}
	}
}
