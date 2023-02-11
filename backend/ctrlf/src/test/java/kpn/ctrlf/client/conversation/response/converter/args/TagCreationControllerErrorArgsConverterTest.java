package kpn.ctrlf.client.conversation.response.converter.args;

import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
import kpn.ctrlf.client.conversation.response.args.TagCreationControllerErrorArgs;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TagCreationControllerErrorArgsConverterTest {

	@Test
	void shouldConversion_ifNameAbsent() {
		ErrorArgs args = new TagCreationControllerErrorArgsConverter().convert();
		assertThat(args.getClass()).isEqualTo(TagCreationControllerErrorArgs.class);
		TagCreationControllerErrorArgs castArgs = (TagCreationControllerErrorArgs) args;
		assertThat(castArgs.getName()).isEmpty();
	}

	@Test
	void shouldConversion() {
		String expectedName = "some.name";

		ErrorArgs args = new TagCreationControllerErrorArgsConverter().convert(expectedName);
		assertThat(args.getClass()).isEqualTo(TagCreationControllerErrorArgs.class);
		TagCreationControllerErrorArgs castArgs = (TagCreationControllerErrorArgs) args;
		assertThat(castArgs.getName()).isEqualTo(expectedName);
	}
}
