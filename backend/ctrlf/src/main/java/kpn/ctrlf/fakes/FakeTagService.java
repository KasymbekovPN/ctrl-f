package kpn.ctrlf.fakes;

import kpn.ctrlf.data.domain.Tag;
import kpn.lib.result.Result;

import java.util.List;

// TODO: 05.02.2023 it is temporary variant
public interface FakeTagService {
	default Result<Tag> save(Tag tag) { return null; }
	default Result<List<Tag>> findAll() { return null; }
	default Result<Tag> update(Tag tag) { return null; }
	default Result<Long> delete(Long id) { return null; }
}
