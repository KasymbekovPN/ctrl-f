import { TAG } from "../../sconst/tag";

const state = {
	tags: new Map()
};

const getters = {
	// tagAttributeDatasource
	// tagIndexes
};

const actions = {
	[TAG.CREATED]: ({commit}, tag) => {
		//<
		console.log(commit);
		console.log(tag);
		//<

		//< impl it
	}
};

const mutations = {};

export default {
	state,
	getters,
	actions,
	mutations
};
