package kpn.ctrlf.data.monitoring;

public interface ChangeMonitoring<D> {
	void traceCreation(D domain);
	void traceUpdating(D domain);
	void traceDeleting(D domain);
}
