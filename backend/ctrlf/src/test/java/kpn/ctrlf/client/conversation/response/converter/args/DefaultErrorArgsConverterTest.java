package kpn.ctrlf.client.conversation.response.converter.args;

import kpn.ctrlf.client.conversation.response.args.DefaultErrorArgs;
import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultErrorArgsConverterTest {

	@Test
	void shouldCheckConversion() {
		ErrorArgs args = new DefaultErrorArgsConverter().convert();
		assertThat(args.getClass()).isEqualTo(DefaultErrorArgs.class);
	}
}
