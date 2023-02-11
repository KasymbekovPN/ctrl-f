package kpn.ctrlf.client.conversation.controller.domain;

import kpn.ctrlf.client.conversation.controller.ArgsConverters;
import kpn.ctrlf.client.conversation.controller.RequestController;
import kpn.ctrlf.client.conversation.controller.ValueConverters;
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

// TODO: 11.02.2023 remake returned
@Controller
public final class TagCreationController implements RequestController<TagCreationController.Request, Result<Tag>> {
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
	public Result<Tag> response(@DestinationVariable String sessionId, Request request) {
		// TODO: 11.02.2023 del
		System.out.println(valueConverter);
		System.out.println(errorArgsConverter);
		//<

		Result<Tag> result = tagService.save(new Tag(null, request.getName()));

		// TODO: 11.02.2023 temp
		Response response = responseConverter.convert(result, valueConverter, errorArgsConverter);
		//<

		return result;
	}

	@Getter
	@Setter
	public final static class Request {
		private String name;
	}
}
