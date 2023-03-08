package kpn.ctrlf.client.conversation.response.converter.args;

import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
import kpn.ctrlf.client.conversation.response.args.TagUpdatingControllerErrorArgs;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TagUpdatingControllerErrorArgsConverterTest {

	@Test
	void shouldCheckConversion_ifInputEmpty() {
		ErrorArgs args = new TagUpdatingControllerErrorArgsConverter().convert();

		assertThat(args.getClass()).isEqualTo(TagUpdatingControllerErrorArgs.class);
		TagUpdatingControllerErrorArgs castArgs = (TagUpdatingControllerErrorArgs) args;
		assertThat(castArgs.getId()).isEmpty();
		assertThat(castArgs.getName()).isEmpty();
	}

	@Test
	void shouldCheckConversion_ifInputContainsOnlyName() {
		String expectedName = "some.name";
		ErrorArgs args = new TagUpdatingControllerErrorArgsConverter().convert(expectedName);

		assertThat(args.getClass()).isEqualTo(TagUpdatingControllerErrorArgs.class);
		TagUpdatingControllerErrorArgs castArgs = (TagUpdatingControllerErrorArgs) args;
		assertThat(castArgs.getId()).isEmpty();
		assertThat(castArgs.getName()).isEqualTo(expectedName);
	}

	@Test
	void shouldCheckConversion_ifInputContainsNameAndId() {
		String expectedName = "some.name";
		long expectedId = 123L;
		ErrorArgs args = new TagUpdatingControllerErrorArgsConverter().convert(expectedName, expectedId);

		assertThat(args.getClass()).isEqualTo(TagUpdatingControllerErrorArgs.class);
		TagUpdatingControllerErrorArgs castArgs = (TagUpdatingControllerErrorArgs) args;
		assertThat(castArgs.getId()).isEqualTo(String.valueOf(expectedId));
		assertThat(castArgs.getName()).isEqualTo(expectedName);
	}
}
