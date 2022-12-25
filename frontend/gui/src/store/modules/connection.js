import { Connection } from "@/connection/connection";
import {
	CONNECTION_CREATE,
	CONNECTION_CONNECT,
	//<
	// CONNECTION_DISCONNECT,
	// CONNECTION_SEND
} from "../actions/connection";

const state = {
	connection: undefined,
	sessionId: undefined
};

const getters = {
	isConnected: state => {
		return state.connection !== undefined && state.connection.connected;
	}
};

const actions = {
	[CONNECTION_CREATE]: ({commit, dispatch}, {clientCreator, connectionHeaders, sessionId}) => {
		//<
		console.log(`action: [${CONNECTION_CREATE}]\ncommit: ${commit}\ndispatch: ${dispatch}\nclientCreator: ${clientCreator}\nconnectionHeaders: ${connectionHeaders}\nsessionId: ${sessionId}`)
		//<
		const connection = new Connection(clientCreator, connectionHeaders);
		//<
		// connection.addSubscription('/topic/????', response => {
		// 	console.log(`-------- greeting response: ${response}`);
		// });

		commit(CONNECTION_CREATE, {connection, sessionId});

		dispatch(CONNECTION_CONNECT);
	},
	[CONNECTION_CONNECT]: ({commit, dispatch}) => {
		//<
		console.log(`action: [${CONNECTION_CONNECT}]\ncommit: ${commit}\ndispatch: ${dispatch}`);
		//<
		commit(CONNECTION_CONNECT);
	},
	//<
	// [CONNECTION_DISCONNECT]: ({commit, dispatch}) => {
	// },
	// [CONNECTION_SEND]: ({commit, dispatch}) => {
	// }
};

const mutations = {
	[CONNECTION_CREATE]: (state, {connection, sessionId}) => {
		//<
		console.log(`mutation: ${CONNECTION_CREATE}\nstate: ${state}\nconnection: ${connection}\nsessionId: ${sessionId}`)
		//<
		state.connection = connection;
		state.sessionId = sessionId;
	},
	[CONNECTION_CONNECT]: state => {
		//<
		console.log(`mutation: ${CONNECTION_CREATE}\nstate: ${state}`);
		//<
		state.connection.connect();
	},
	//<
	// [CONNECTION_DISCONNECT]: state => {
	// },
	// [CONNECTION_SEND]: state => {
	// }
};

export default {
	state,
	getters,
	actions,
	mutations
};
