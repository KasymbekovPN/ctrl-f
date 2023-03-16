package kpn.ctrlf.data.changing;

public abstract class BaseDoer<D> implements Doer<D> {
	protected String session;
	protected D value;

	@Override
	public Doer<D> session(String session) {
		this.session = session;
		return this;
	}

	@Override
	public Doer<D> value(D value) {
		this.value = value;
		return this;
	}
}
