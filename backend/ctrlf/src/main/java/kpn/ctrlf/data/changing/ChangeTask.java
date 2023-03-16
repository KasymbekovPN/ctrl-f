package kpn.ctrlf.data.changing;

import java.util.Set;

public interface ChangeTask<D> {
	D getValue();
	Set<String> excluded();
}
