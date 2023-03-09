import config from "../../../config";
import { SIGNAL } from "../../sconst/signal";
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
		body: name
	});
};

const actOnTagUpdatingRequest = ({dispatch}, {id, name}) => {
	dispatch(CONNECTION.SEND, {
		destination: config.requests.tag.update,
		headers: {},
		body: {id, name}
	});
};

const actOnTagRemovingRequest = ({dispatch}, id) => {
	dispatch(CONNECTION.SEND, {
		destination: config.requests.tag.delete,
		headers: {},
		body: {id}
	});
};

const actOnTagLoadingResponse = ({commit}, tags) => {
	commit(TAG.RESPONSE.LOAD, tags);
};

const actOnTagCreationResponse = ({commit}, tag) => {
	commit(TAG.RESPONSE.CREATE, tag);
};

const actOnTagUpdatingResponse = ({commit}, tag) => {
	commit(TAG.RESPONSE.UPDATE, tag);
};

const actOnTagRemovingResponse = ({commit}, id) => {
	commit(TAG.RESPONSE.REMOVE, id);
};

const actOnTagCleaning = ({commit}) => {
	commit(TAG.STORAGE.CLEAR);
};

const actOnTagSelectItem = ({commit, dispatch}, id) => {
	commit(TAG.SELECT.ITEM, id);
	dispatch(SIGNAL.MODAL.TAG.ADD.SHOW);
};

export {
	actOnTagLoadingRequest,
	actOnTagCreationRequest,
	actOnTagUpdatingRequest,
	actOnTagRemovingRequest,
	actOnTagLoadingResponse,
	actOnTagCreationResponse,
	actOnTagUpdatingResponse,
	actOnTagRemovingResponse,
	actOnTagCleaning,
	actOnTagSelectItem
};
