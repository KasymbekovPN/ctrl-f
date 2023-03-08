package kpn.ctrlf.fakes;

import kpn.ctrlf.data.domain.Tag;
import kpn.lib.result.ImmutableResult;
import kpn.lib.result.Result;
import org.springframework.stereotype.Component;

import java.util.*;

// TODO: 05.02.2023 it is temporary variant
@Component
public final class FakeTagServiceImpl implements FakeTagService {
	private static final Map<Long, Tag> INIT_MAP = new HashMap<>();
	static {
		for (long i = 0; i < 10; i++) {
			INIT_MAP.put(i, new Tag(i, "name " + String.valueOf(i)));
		}
	}

	private final Map<Long, Tag> storage;
	private final Set<String> names = new HashSet<>();

	public FakeTagServiceImpl() {
		storage = INIT_MAP;
	}

	public FakeTagServiceImpl(Map<Long, Tag> storage) {
		this.storage = storage;
	}

	public Result<Tag> save(Tag tag) {
//		return ImmutableResult.<Tag>bFail("domain.creation.tag.error.already-exist").arg(tag.getName()).build();
		// TODO: 06.02.2023 ??
		String name = tag.getName();
		if (names.contains(name)){
			return ImmutableResult.<Tag>bFail("domain.tag.saving.fail").arg(name).build();
		}

		long id = storage.size();
		Tag newTag = new Tag(id, name);
		storage.put(id, newTag);

		return ImmutableResult.<Tag>ok(newTag);
	}

	@Override
	public Result<List<Tag>> findAll() {
		return ImmutableResult.<List<Tag>>ok(new ArrayList<>(storage.values()));
		//<
//		return ImmutableResult.<List<Tag>>bFail("domain.tag.loading.fail")
//			.arg("some went wrong")
//			.build();
	}

	@Override
	public Result<Tag> update(Tag tag) {
//		return ImmutableResult.<Tag>bFail("domain.updating.tag.error.already-exist")
//			.arg(tag.getId())
//			.arg(tag.getName())
//			.build();
		// TODO: 08.03.2023 !!!
		Long id = tag.getId();
		String name = tag.getName();
		if (names.contains(name)){
			return ImmutableResult.<Tag>bFail("domain.tag.updating.fail")
				.arg(id)
				.arg(name)
				.build();
		}

		storage.put(id, tag);
		return ImmutableResult.<Tag>ok(tag);
	}
}
