
const tagAttributeDatasource = state => (index, attribute) => {
	return state.tags !== undefined && state.tags.has(index)
		? state.tags.get(index)[attribute]
		: undefined;
};

const tagIndexes = state => {
	return state.tags !== undefined
		? state.tags.keys()
		: new Map().keys();
};

export {
	tagAttributeDatasource,
	tagIndexes
};
