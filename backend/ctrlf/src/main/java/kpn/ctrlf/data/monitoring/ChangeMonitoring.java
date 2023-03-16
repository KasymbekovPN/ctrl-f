package kpn.ctrlf.data.monitoring;

// TODO: 16.03.2023 rename to ChangeManager
// TODO: 16.03.2023 rename methods
public interface ChangeMonitoring<D> {
	void traceCreation(D domain);
	void traceUpdating(D domain);
	void traceDeleting(D domain);
}
