
const mutateOnTagLoaded = (state, tags) => {
	state.tags.clear();
	for (const tag of tags){
		state.tags.set(tag.id, tag);
	}
};

const mutateOnTagCreated = (state, tag) => {
	if (!state.tags.has(tag.id)){
		state.tags.set(tag.id, tag);
	}
};

const mutateOnTagUpdated = (state, tag) => {
	if (state.tags.has(tag.id)){
		state.tags.set(tag.id, tag);
	}
};

const mutateOnTagRemoved = (state, id) => {
	state.tags.delete(id);
};

export {
	mutateOnTagLoaded,
	mutateOnTagCreated,
	mutateOnTagUpdated,
	mutateOnTagRemoved
};
