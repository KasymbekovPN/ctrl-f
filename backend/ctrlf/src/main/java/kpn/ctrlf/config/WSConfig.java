package kpn.ctrlf.config;

import kpn.ctrlf.session.SessionBridge;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.support.ExecutorChannelInterceptor;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

import java.util.Optional;
import java.util.function.Function;

@Configuration
@EnableWebSocketMessageBroker
public class WSConfig implements WebSocketMessageBrokerConfigurer {
	@Autowired
	private SessionBridge sessionBridge;

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic");
		registry.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {

		registration.interceptors(new SessionIdExecutorChannelInterceptor(
			sessionBridge,
			new CoreSessionIdExtractor(),
			new GuiSessionIdExtractor()
		));
		WebSocketMessageBrokerConfigurer.super.configureClientInboundChannel(registration);
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry
			.addEndpoint("/greetingRequest")
			.setAllowedOrigins("*");
	}

	@Override
	public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
		registry.addDecoratorFactory(new WebSocketHandlerDecoratorFactory() {
			@Override
			public WebSocketHandler decorate(WebSocketHandler handler) {
				return new WebSocketHandlerDecorator(handler){

					@Override
					public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
						sessionBridge.eraseCorrespondence(session.getId());
						super.afterConnectionClosed(session, closeStatus);
					}
				};
			}
		});
		WebSocketMessageBrokerConfigurer.super.configureWebSocketTransport(registry);
	}

	public static class CoreSessionIdExtractor implements Function<MessageHeaders, Optional<String>>{
		private static final String KEY = "simpSessionId";
		@Override
		public Optional<String> apply(MessageHeaders headers) {
			return headers.containsKey(KEY)
				? Optional.of(String.valueOf(headers.get(KEY)))
				: Optional.empty();
		}
	}

	public static class GuiSessionIdExtractor implements Function<MessageHeaders, Optional<String>>{
		private static final String KEY = "simpDestination";
		@Override
		public Optional<String> apply(MessageHeaders headers) {
			if (headers.containsKey(KEY)){
				String[] split = String.valueOf(headers.get(KEY)).split("/");
				return Optional.of(split[split.length - 1]);
			}
			return Optional.empty();
		}
	}

	@RequiredArgsConstructor
	public static class SessionIdExecutorChannelInterceptor implements ExecutorChannelInterceptor{
		private final SessionBridge sessionBridge;
		private final Function<MessageHeaders, Optional<String>> coreSessionIdExtractor;
		private final Function<MessageHeaders, Optional<String>> guiSessionIdExtractor;

		@Override
		public Message<?> beforeHandle(Message<?> message, MessageChannel channel, MessageHandler handler) {
			Optional<String> maybeCoreSessionId = coreSessionIdExtractor.apply(message.getHeaders());
			Optional<String> maybeGuiSessionId = guiSessionIdExtractor.apply(message.getHeaders());
			maybeCoreSessionId.ifPresent(value -> maybeGuiSessionId.ifPresent(s -> sessionBridge.setCorrespondence(value, s)));

			return ExecutorChannelInterceptor.super.beforeHandle(message, channel, handler);
		}
	}
}
