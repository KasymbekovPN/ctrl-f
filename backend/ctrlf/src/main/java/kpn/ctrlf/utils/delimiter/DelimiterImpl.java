package kpn.ctrlf.utils.delimiter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DelimiterImpl implements Delimiter {
	private final String first;
	private final String other;

	private boolean isFirst = true;

	@Override
	public String next() {
		boolean b = isFirst;
		isFirst = false;
		return b ? first : other;
	}
}
