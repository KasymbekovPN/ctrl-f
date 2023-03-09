package kpn.ctrlf.client.conversation.response.converter.args;

import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
import kpn.ctrlf.client.conversation.response.args.TagDeletingControllerErrorArgs;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TagDeletingControllerErrorArgsConverterTest {
	private static final String DEFAULT_ID = "?";

	@Test
	void shouldCheckConversion_ifInputEmpty() {
		ErrorArgs args = new TagDeletingControllerErrorArgsConverter().convert();

		assertThat(args.getClass()).isEqualTo(TagDeletingControllerErrorArgs.class);
		TagDeletingControllerErrorArgs castArgs = (TagDeletingControllerErrorArgs) args;
		assertThat(castArgs.getId()).isEqualTo(DEFAULT_ID);
	}

	@Test
	void shouldCheckConversion() {
		long expectedId = 123L;
		ErrorArgs args = new TagDeletingControllerErrorArgsConverter().convert(expectedId);

		assertThat(args.getClass()).isEqualTo(TagDeletingControllerErrorArgs.class);
		TagDeletingControllerErrorArgs castArgs = (TagDeletingControllerErrorArgs) args;
		assertThat(castArgs.getId()).isEqualTo(String.valueOf(expectedId));
	}
}
