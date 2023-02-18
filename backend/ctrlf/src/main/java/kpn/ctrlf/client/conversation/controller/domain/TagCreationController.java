package kpn.ctrlf.client.conversation.controller.domain;

import kpn.ctrlf.client.conversation.controller.*;
import kpn.ctrlf.client.conversation.response.Response;
import kpn.ctrlf.client.conversation.response.converter.ResponseConverter;
import kpn.ctrlf.client.conversation.response.converter.args.ErrorArgsConverter;
import kpn.ctrlf.client.conversation.response.converter.value.ValueConverter;
import kpn.ctrlf.data.domain.Tag;
import kpn.ctrlf.fakes.FakeTagService;
import kpn.lib.result.Result;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@ControllerConverter(Controllers.TAG_CREATION)
@Controller
@Setter
public final class TagCreationController implements RequestController<TagCreationController.Request> {

	@Autowired
	private FakeTagService tagService;

	@Autowired
	private ResponseConverter responseConverter;

	@Autowired
	@Qualifier(ValueConverters.TAG_CREATION)
	private ValueConverter valueConverter;

	@Autowired
	@Qualifier(ArgsConverters.TAG_CREATION)
	private ErrorArgsConverter errorArgsConverter;

	@Override
	@MessageMapping("/tagCreationRequest/{sessionId}")
	@SendTo("/topic/tagCreationResponse/{sessionId}")
	public Response response(@DestinationVariable String sessionId, Request request) {
		Result<Tag> result = tagService.save(new Tag(null, request.getName()));
		return responseConverter.convert(result, valueConverter, errorArgsConverter);
	}

	@Getter
	@Setter
	public final static class Request {
		private String name;
	}
}
