package kpn.ctrlf.client.conversation.controller.domain;

import kpn.ctrlf.client.conversation.controller.ControllerConverter;
import kpn.ctrlf.client.conversation.controller.Controllers;
import kpn.ctrlf.client.conversation.controller.RequestController;
import kpn.ctrlf.client.conversation.response.Response;
import kpn.ctrlf.client.conversation.response.converter.ResponseConverter;
import kpn.ctrlf.client.conversation.response.converter.args.ErrorArgsConverter;
import kpn.ctrlf.client.conversation.response.converter.value.ValueConverter;
import kpn.ctrlf.fakes.FakeTagService;
import kpn.lib.result.Result;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@ControllerConverter(Controllers.TAG_DELETING)
@Controller
@Setter
public final class TagDeletingController implements RequestController<TagDeletingController.Request> {

	@Autowired
	private FakeTagService tagService;
	@Autowired
	private ResponseConverter responseConverter;

	private ValueConverter valueConverter;
	private ErrorArgsConverter errorArgsConverter;

	@MessageMapping("/tagDeletingRequest/{sessionId}")
	@SendTo("/topic/tagDeletingResponse/{sessionId}")
	@Override
	public Response response(@DestinationVariable String sessionId, Request request) {
		Result<Long> result = tagService.delete(request.getId());
		return responseConverter.convert(result, valueConverter, errorArgsConverter);
	}

	@Getter
	@Setter
	public final static class Request {
		private Long id;
	}
}
