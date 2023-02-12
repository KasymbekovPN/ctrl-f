package kpn.ctrlf.client.conversation.response;

import lombok.Getter;

@Getter
public class ErrorResponse extends SimpleResponse{
	private final String code;

	public ErrorResponse(String code) {
		super(false);
		this.code = code;
	}
}
