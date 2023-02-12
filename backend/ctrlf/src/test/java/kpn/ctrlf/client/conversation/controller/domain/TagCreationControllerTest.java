package kpn.ctrlf.client.conversation.controller.domain;

import kpn.ctrlf.client.conversation.response.Response;
import kpn.ctrlf.client.conversation.response.ResponseImpl;
import kpn.ctrlf.client.conversation.response.converter.ResponseConverter;
import kpn.ctrlf.client.conversation.response.value.Value;
import kpn.ctrlf.data.domain.Tag;
import kpn.ctrlf.fakes.FakeTagService;
import kpn.lib.result.ImmutableResult;
import kpn.lib.result.Result;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class TagCreationControllerTest {
	private static final String NAME = "some.name";
	private static final Long ID = 123L;

	private static final Result<Tag> RESULT = ImmutableResult.<Tag>ok(new Tag(ID, NAME));

	@Test
	void shouldCheckResponseMethod() {
		TagCreationController.Request request = new TagCreationController.Request();

		TagCreationController controller = new TagCreationController();
		controller.setTagService(createTagService());
		controller.setResponseConverter(createResponseConverter());

		Response response = controller.response("", request);
		assertThat(response.getValue().getClass()).isEqualTo(TestValue.class);
		TestValue castValue = (TestValue) response.getValue();
		assertThat(castValue.getName()).isEqualTo(NAME);
	}

	private FakeTagService createTagService(){
		TestFakeTagService service = Mockito.mock(TestFakeTagService.class);
		Mockito
			.when(service.save(Mockito.anyObject()))
			.thenReturn(RESULT);

		return service;
	}

	private ResponseConverter createResponseConverter() {
		TestResponseConverter converter = Mockito.mock(TestResponseConverter.class);
		Mockito
			.when(converter.convert(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject()))
			.thenReturn(new ResponseImpl(new TestValue(NAME)));

		return converter;
	}

	private abstract static class TestFakeTagService implements FakeTagService {}
	private abstract static class TestResponseConverter implements ResponseConverter {}

	@RequiredArgsConstructor
	@Getter
	private static class TestValue implements Value {
		private final String name;
	}
}
