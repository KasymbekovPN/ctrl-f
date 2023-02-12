import { NOTIFICATION } from "../../sconst/notification";

const state = {};

const getters = {};

const actions = {
	[NOTIFICATION.ERROR]: ({dispatch}, {code, args}) => {
		//<
		console.log(dispatch);
		console.log(code);
		console.log(args);
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
