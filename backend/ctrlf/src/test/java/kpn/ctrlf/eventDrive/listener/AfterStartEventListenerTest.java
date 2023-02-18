package kpn.ctrlf.eventDrive.listener;

import kpn.ctrlf.client.conversation.controller.binding.Binder;
import kpn.ctrlf.eventDrive.event.AfterStartEvent;
import kpn.lib.result.ImmutableResult;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebServerApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

class AfterStartEventListenerTest {


	@Test
	void shouldCheckEventProcessing_ifSuccess() {
		TestContext context = new TestContext();
		AfterStartEventListener listener = new AfterStartEventListener(context, createSuccessBinder());

		listener.processEvent(new AfterStartEvent(this));
		assertThat(context.isClosed()).isFalse();
	}

	private Binder createSuccessBinder() {
		TestBinder binder = Mockito.mock(TestBinder.class);
		Mockito
			.when(binder.bind())
			.thenReturn(ImmutableResult.<Void>ok(null));

		return binder;
	}

	@Test
	void shouldCheckJEventProcessing_ifConverterControllerBinderReturnFail() {
		TestContext context = new TestContext();
		AfterStartEventListener listener = new AfterStartEventListener(context, createFailBinder());

		listener.processEvent(new AfterStartEvent(this));
		assertThat(context.isClosed()).isTrue();
	}

	private Binder createFailBinder() {
		TestBinder binder = Mockito.mock(TestBinder.class);
		Mockito
			.when(binder.bind())
			.thenReturn(ImmutableResult.<Void>fail(""));

		return binder;
	}

	private static abstract class TestBinder implements Binder {}

	private static class TestContext extends AnnotationConfigReactiveWebServerApplicationContext {
		@Getter
		private boolean closed;

		@Override
		protected void doClose() {
			closed = true;
		}
	}
}
