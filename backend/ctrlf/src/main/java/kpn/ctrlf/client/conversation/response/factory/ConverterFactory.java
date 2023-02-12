package kpn.ctrlf.client.conversation.response.factory;

import kpn.ctrlf.client.conversation.controller.Controllers;
import kpn.ctrlf.client.conversation.response.converter.ResponseConverter;


// TODO: 12.02.2023 del
public interface ConverterFactory {
	ResponseConverter create(Controllers id);
}
