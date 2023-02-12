package kpn.ctrlf.client.conversation.response;

import kpn.ctrlf.client.conversation.response.args.ErrorArgs;
import lombok.Getter;

@Getter
public final class ErrorArgsResponse extends ErrorResponse{
	private final ErrorArgs args;

	public ErrorArgsResponse(String code, ErrorArgs args) {
		super(code);
		this.args  = args;
	}
}
