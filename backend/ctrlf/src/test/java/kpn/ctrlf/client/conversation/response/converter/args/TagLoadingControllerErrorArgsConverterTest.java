package kpn.ctrlf.client.conversation.response.converter.args;

import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
import kpn.ctrlf.client.conversation.response.args.TagLoadingControllerErrorArgs;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TagLoadingControllerErrorArgsConverterTest {

	@Test
	void shouldCheckConversion_ifCauseAbsent() {
		TagLoadingControllerErrorArgsConverter converter = new TagLoadingControllerErrorArgsConverter();
		ErrorArgs args = converter.convert();

		assertThat(args.getClass()).isEqualTo(TagLoadingControllerErrorArgs.class);
		TagLoadingControllerErrorArgs castArgs = (TagLoadingControllerErrorArgs) args;
		assertThat(castArgs.getCause()).isEmpty();
	}

	@Test
	void shouldCheckConversion() {
		String expected = "some.cause";

		TagLoadingControllerErrorArgsConverter converter = new TagLoadingControllerErrorArgsConverter();
		ErrorArgs args = converter.convert(expected);

		assertThat(args.getClass()).isEqualTo(TagLoadingControllerErrorArgs.class);
		TagLoadingControllerErrorArgs castArgs = (TagLoadingControllerErrorArgs) args;
		assertThat(castArgs.getCause()).isEqualTo(expected);
	}
}
