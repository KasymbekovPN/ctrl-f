package kpn.ctrlf.fakes;

import kpn.ctrlf.data.domain.Tag;
import kpn.lib.result.ImmutableResult;
import kpn.lib.result.Result;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// TODO: 05.02.2023 it is temporary variant
@Component
public final class FakeTagServiceImpl implements FakeTagService {
	private final Map<Long, Tag> storage;
	private final Set<String> names = new HashSet<>();

	public FakeTagServiceImpl() {
		storage = new HashMap<>();
	}

	public FakeTagServiceImpl(Map<Long, Tag> storage) {
		this.storage = storage;
	}


//				<!-- <div class="content__text">
//	<span>{{message.name}}</span>
//				<!-- <i class="material-icons">{{message.icon}}</i> -->
//			<!-- </div>
//			<div class="content_buttons">
//				<button v-if="rightButton.length">{{rightButton}}</button>
//				<button v-if="leftButton.length">{{leftButton}}</button>
//			</div> -->

	public Result<Tag> save(Tag tag) {
		return ImmutableResult.<Tag>bFail("domain.creation.tag.error.already-exist").arg(tag.getName()).build();
		// TODO: 06.02.2023 ??
//		String name = tag.getName();
//		if (names.contains(name)){
//			return ImmutableResult.<Tag>bFail("domain.tag.saving.fail").arg(name).build();
//		}
//
//		long id = storage.size();
//		Tag newTag = new Tag(id, name);
//		storage.put(id, newTag);
//
//		return ImmutableResult.<Tag>ok(newTag);
	}
}
