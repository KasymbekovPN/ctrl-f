package kpn.ctrlf.utils.delimiter;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DelimiterImplTest {


	@Test
	void shouldCheckNextGetting() {
		String first = "first";
		String other = "other";

		DelimiterImpl delimiter = new DelimiterImpl(first, other);
		for (int i = 0; i < 100; i++) {
			String next = delimiter.next();
			String expected = i == 0 ? first : other;
			assertThat(next).isEqualTo(expected);
		}
	}
}
