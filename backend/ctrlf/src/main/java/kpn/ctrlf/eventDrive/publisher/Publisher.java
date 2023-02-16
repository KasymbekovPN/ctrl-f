package kpn.ctrlf.eventDrive.publisher;

public interface Publisher<T> {
	void publisher(T input);
}
