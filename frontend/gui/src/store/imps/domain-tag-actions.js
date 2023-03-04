//< del
import config from "../../../config";
import { CONNECTION } from "../../sconst/connection";

const actOnTagLoadingRequest = ({dispatch}) => {
	dispatch(CONNECTION.SEND, {
		destination: config.requests.tag.load,
		headers: {},
		body: {}
	});
};

const processTagCreationRequest = ({dispatch}, {name}) => {
	dispatch(CONNECTION.SEND, {
		destination: config.requests.tag.create,
		headers: {},
		body: {name}
	});
};

const processTagUpdatingRequest = ({dispatch}, {id, name}) => {
	dispatch(CONNECTION.SEND, {
		destination: config.requests.tag.update,
		headers: {},
		body: {id, name}
	});
};

const processTagDeletingRequest = ({dispatch}, {id})  => {
	dispatch(CONNECTION.SEND, {
		destination: config.requests.tag.delete,
		headers: {},
		body: {id}
	});
};

export {
	actOnTagLoadingRequest,
	processTagCreationRequest,
	processTagUpdatingRequest,
	processTagDeletingRequest
};
