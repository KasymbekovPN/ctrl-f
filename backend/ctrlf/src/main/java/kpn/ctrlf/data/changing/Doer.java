package kpn.ctrlf.data.changing;

import kpn.ctrlf.data.monitoring.ChangeMonitoringImpl;

public interface Doer<D> {
	default Doer<D> session(String session){ return this;}
	default Doer<D> value(D value){ return this; }
	ChangeMonitoringImpl<D> apply();
}
