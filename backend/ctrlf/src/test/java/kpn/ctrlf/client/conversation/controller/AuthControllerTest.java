package kpn.ctrlf.client.conversation.controller;

import kpn.ctrlf.client.conversation.request.AuthControllerRequest;
import kpn.ctrlf.client.conversation.response.ErrorArgsResponse;
import kpn.ctrlf.client.conversation.response.OkResponse;
import kpn.ctrlf.client.conversation.response.Response;
import kpn.ctrlf.client.conversation.response.args.AuthControllerErrorArgs;
import kpn.ctrlf.client.conversation.response.factory.ResponseFactoryImpl;
import kpn.ctrlf.client.conversation.response.value.AuthControllerValue;
import kpn.ctrlf.data.domain.User;
import kpn.ctrlf.secure.UserSecureService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

class AuthControllerTest {

	private static final String USER_NAME = "some.username";
	private static final String PASSWORD = "some.password";
	private static final String SESSION_ID = "some.sessionId";
	private static final User USER = new User(USER_NAME, PASSWORD);

	@Test
	void shouldCheckResponseMethod_ifCredentialIsInvalid() {
		AuthController controller
			= new AuthController(new ResponseFactoryImpl(), createFailSecureService(), createConverter());
		Response response = controller.response(SESSION_ID, new AuthControllerRequest());

		assertThat(response.getClass()).isEqualTo(ErrorArgsResponse.class);
		ErrorArgsResponse castResponse = (ErrorArgsResponse) response;
		assertThat(castResponse.isSuccess()).isFalse();
		assertThat(castResponse.getCode()).isEqualTo("auth.wrong-credential");
		assertThat(castResponse.getArgs().getClass()).isEqualTo(AuthControllerErrorArgs.class);
		AuthControllerErrorArgs castArgs = (AuthControllerErrorArgs) castResponse.getArgs();
		assertThat(castArgs.getUsername()).isEqualTo(USER_NAME);
	}

	@Test
	void shouldCheckResponseMethod() {
		AuthController controller
			= new AuthController(new ResponseFactoryImpl(), createSuccessSecureService(), createConverter());
		Response response = controller.response(SESSION_ID, new AuthControllerRequest());

		assertThat(response.getClass()).isEqualTo(OkResponse.class);
		OkResponse castResponse = (OkResponse) response;
		assertThat(castResponse.isSuccess()).isTrue();
		assertThat(castResponse.getValue().getClass()).isEqualTo(AuthControllerValue.class);
		AuthControllerValue castValue = (AuthControllerValue) castResponse.getValue();
		assertThat(castValue.getToken()).isEqualTo(SESSION_ID);
		assertThat(castValue.getUsername()).isEqualTo(USER_NAME);
	}

	private UserSecureService<User> createFailSecureService(){
		TestUserSecureService service = Mockito.mock(TestUserSecureService.class);
		Mockito
			.when(service.checkCredential(USER))
			.thenReturn(false);
		return  service;
	}

	private UserSecureService<User> createSuccessSecureService(){
		TestUserSecureService service = Mockito.mock(TestUserSecureService.class);
		Mockito
			.when(service.checkCredential(USER))
			.thenReturn(true);
		return  service;
	}

	private TestConverter createConverter(){
		TestConverter converter = Mockito.mock(TestConverter.class);
		Mockito
			.when(converter.apply(Mockito.anyObject()))
			.thenReturn(USER);

		return converter;
	}

	private static abstract class TestUserSecureService implements UserSecureService<User> {}
	private static abstract class TestConverter implements Function<AuthControllerRequest, User> {}

}
