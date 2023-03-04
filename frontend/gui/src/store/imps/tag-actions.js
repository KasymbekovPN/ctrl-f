import config from "../../../config";
import { CONNECTION } from "../../sconst/connection";
import { TAG } from "../../sconst/tag";

const actOnTagLoadingRequest = ({dispatch}) => {
	dispatch(CONNECTION.SEND, {
		destination: config.requests.tag.load,
		headers: {},
		body: {}
	});
};

const actOnTagCreationRequest = ({dispatch}, name) => {
	dispatch(CONNECTION.SEND, {
		destination: config.requests.tag.create,
		headers: {},
		body: {name}
	});
};

//<
// const actOnTagUpdatingRequest = ({dispatch}, {id, name}) => {};

//<
// const actOnTagRemovingRequest = ({dispatch}, id) => {};

const actOnTagLoadingResponse = ({commit}, tags) => {
	commit(TAG.RESPONSE.LOAD, tags);
};

const actOnTagCreationResponse = ({commit}, tag) => {
	commit(TAG.RESPONSE.CREATE, tag);
};

//<
// const actOnTagUpdatingResponse = ({commit}, tag) => {};

//<
// const actOnTagRemovingResponse = ({commit}, id) => {};

const actOnTagCleaning = ({commit}) => {
	commit(TAG.STORAGE.CLEAR);
};

export {
	actOnTagLoadingRequest,
	actOnTagCreationRequest,
	//<
	// actOnTagUpdatingRequest,
	// actOnTagRemovingRequest,
	actOnTagLoadingResponse,
	actOnTagCreationResponse,
	//<
	// actOnTagUpdatingResponse,
	// actOnTagRemovingResponse,
	actOnTagCleaning
};
