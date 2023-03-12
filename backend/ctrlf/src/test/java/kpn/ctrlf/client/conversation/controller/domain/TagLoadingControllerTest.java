package kpn.ctrlf.client.conversation.controller.domain;

import kpn.ctrlf.client.conversation.request.EmptyRequest;
import kpn.ctrlf.client.conversation.response.OkResponse;
import kpn.ctrlf.client.conversation.response.converter.ResponseConverter;
import kpn.ctrlf.client.conversation.response.value.Value;
import kpn.ctrlf.data.domain.Tag;
import kpn.ctrlf.fakes.FakeTagService;
import kpn.ctrlf.subscription.SubscriptionHolderImpl;
import kpn.lib.result.ImmutableResult;
import kpn.lib.result.Result;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.linesOf;

class TagLoadingControllerTest {
	private static final String SESSION_ID = "some.session.id";
	private static final String NAME = "some.name";
	private static final Long ID = 123L;

	private static final Result<List<Tag>> RESULT = ImmutableResult.<List<Tag>>ok(List.of(new Tag(ID, NAME)));

	@Test
	void shouldCheckResponseMethod() {
		SubscriptionHolderImpl<String> subscriptionHolder = new SubscriptionHolderImpl<>();
		TagLoadingController controller = new TagLoadingController();
		controller.setTagService(createTagService());
		controller.setResponseConverter(createResponseConverter());
		controller.setSubscriptionHolder(subscriptionHolder);

		OkResponse response = (OkResponse) controller.response(SESSION_ID, new EmptyRequest());
		assertThat(response.getClass()).isEqualTo(OkResponse.class);
		TestValue castValue = (TestValue) response.getValue();
		assertThat(castValue.getName()).isEqualTo(NAME);
		assertThat(subscriptionHolder.getSubscribers().size()).isEqualTo(1);
		assertThat(subscriptionHolder.getSubscribers().contains(SESSION_ID)).isTrue();
	}

	private FakeTagService createTagService(){
		TestFakeTagService service = Mockito.mock(TestFakeTagService.class);
		Mockito
			.when(service.findAll())
			.thenReturn(RESULT);

		return service;
	}

	private ResponseConverter createResponseConverter() {
		TestResponseConverter converter = Mockito.mock(TestResponseConverter.class);
		Mockito
			.when(converter.convert(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject()))
			.thenReturn(new OkResponse(new TestValue(NAME)));

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
