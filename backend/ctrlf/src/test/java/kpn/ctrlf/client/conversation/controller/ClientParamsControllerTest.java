package kpn.ctrlf.client.conversation.controller;

import kpn.ctrlf.client.conversation.request.EmptyRequest;
import kpn.ctrlf.client.conversation.response.OkResponse;
import kpn.ctrlf.client.conversation.response.factory.ResponseFactoryImpl;
import kpn.ctrlf.client.conversation.response.value.ClientParamsControllerValue;
import kpn.ctrlf.client.params.ClientParams;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClientParamsControllerTest {

	@Test
	void shouldCheckResponseMethod() {
		String expectedLocale = "en";
		ClientParams clientParams = new ClientParams(expectedLocale);
		OkResponse response = (OkResponse) new ClientParamsController(
			new ResponseFactoryImpl(),
			clientParams
		).response("", new EmptyRequest());

		assertThat(response.isSuccess()).isTrue();
		assertThat(response.getValue().getClass()).isEqualTo(ClientParamsControllerValue.class);
		ClientParamsControllerValue castValue = (ClientParamsControllerValue) response.getValue();
		assertThat(castValue.getLocale()).isEqualTo(expectedLocale);
	}
}
