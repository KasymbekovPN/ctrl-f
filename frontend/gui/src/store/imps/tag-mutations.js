const mutateOnTagLoaded = (state, tags) => {
	state.tags.clear();
	for (const tag of tags){
		state.tags.set(tag.id, tag);
	}
	//<
	console.log('mutateOnTagLoaded');
	console.log(state.tags);
	//<
};

const mutateOnTagCreated = (state, tag) => {
	if (!state.tags.has(tag.id)){
		state.tags.set(tag.id, tag);
	}
	//<
	console.log('mutateOnTagCreated');
	console.log(state.tags);
	//<
};

const mutateOnTagUpdated = (state, tag) => {
	if (state.tags.has(tag.id)){
		state.tags.set(tag.id, tag);
	}
	//<
	console.log('mutateOnTagUpdated');
	console.log(state.tags);
	//<
};

const mutateOnTagRemoved = (state, id) => {
	state.tags.delete(id);
	//<
	console.log('mutateOnTagRemoved');
	console.log(state.tags);
	//<
};

const mutateOnTagClearing = state => {
	state.tags.clear();
	//<
	console.log('mutateOnTagClearing');
	//<
};

export {
	mutateOnTagLoaded,
	mutateOnTagCreated,
	mutateOnTagUpdated,
	mutateOnTagRemoved,
	mutateOnTagClearing
};
