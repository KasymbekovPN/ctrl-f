//< del it

const state = {
	data: undefined
};

const getters = {
	getDomainAttribute: state => (id, attribute) => {
		if (state.data === undefined){
			state.data = new Map();
			for (let i = 0; i < 10; i++){
				state.data.set(i, {
					id: i,
					name: `name ${i}`,
					n: 123.4567,
					bbb: 12
				});
			}
		}

		return state.data.get(id)[attribute];
	}
};

const actions = {
	testCellChange: ({commit}) => {
		commit('testCellChange');
	}
};

const mutations = {
	testCellChange: state => {
		for (const [key, value] of state.data){
			const id = value.id + 1;
			state.data.set(key, {
				id: id,
				name: `name ${id}`
			});
		}
	}
};

export default {
	state,
	getters,
	actions,
	mutations
};
