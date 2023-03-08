package kpn.ctrlf.client.conversation.controller.domain;

import kpn.ctrlf.client.conversation.controller.ControllerConverter;
import kpn.ctrlf.client.conversation.controller.Controllers;
import kpn.ctrlf.client.conversation.controller.RequestController;
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
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@ControllerConverter(Controllers.TAG_UPDATING)
@Controller
@Setter
public final class TagUpdatingController implements RequestController<TagUpdatingController.Request> {
	@Autowired
	private FakeTagService tagService;
	@Autowired
	private ResponseConverter responseConverter;

	private ValueConverter valueConverter;
	private ErrorArgsConverter errorArgsConverter;

	@Override
	@MessageMapping("/tagUpdatingRequest/{sessionId}")
	@SendTo("/topic/tagUpdatingResponse/{sessionId}")
	public Response response(String sessionId, Request request) {
		Result<Tag> result = tagService.update(new Tag(request.getId(), request.getName()));
		return responseConverter.convert(result, valueConverter, errorArgsConverter);
	}

	@Getter
	public final static class Request {
		private Long id;
		private String name;
	}
}
