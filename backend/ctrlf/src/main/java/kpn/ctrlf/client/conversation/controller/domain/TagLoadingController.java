package kpn.ctrlf.client.conversation.controller.domain;

import kpn.ctrlf.client.conversation.controller.ControllerConverter;
import kpn.ctrlf.client.conversation.controller.Controllers;
import kpn.ctrlf.client.conversation.controller.RequestController;
import kpn.ctrlf.client.conversation.request.EmptyRequest;
import kpn.ctrlf.client.conversation.response.Response;
import kpn.ctrlf.client.conversation.response.converter.ResponseConverter;
import kpn.ctrlf.client.conversation.response.converter.args.ErrorArgsConverter;
import kpn.ctrlf.client.conversation.response.converter.value.ValueConverter;
import kpn.ctrlf.data.domain.Tag;
import kpn.ctrlf.fakes.FakeTagService;
import kpn.lib.result.Result;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@ControllerConverter(Controllers.TAG_LOADING)
@Controller
@Setter
public final class TagLoadingController implements RequestController<EmptyRequest> {

	@Autowired
	private FakeTagService tagService;
	@Autowired
	private ResponseConverter responseConverter;

	private ValueConverter valueConverter;
	private ErrorArgsConverter errorArgsConverter;

	@Override
	@MessageMapping("/tagLoadingRequest/{sessionId}")
	@SendTo("/topic/tagLoadingResponse/{sessionId}")
	public Response response(@DestinationVariable String sessionId, EmptyRequest request) {
		Result<List<Tag>> result = tagService.findAll();
		return responseConverter.convert(result, valueConverter, errorArgsConverter);
	}
}
