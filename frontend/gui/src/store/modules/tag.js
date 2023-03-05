import { TAG } from "../../sconst/tag";
import {
	actOnTagCleaning,
	actOnTagCreationRequest,
	actOnTagCreationResponse,
	actOnTagLoadingRequest,
	actOnTagLoadingResponse
} from "../imps/tag-actions";
import {
	tagAttributeDatasource,
	tagIndexes
} from "../imps/tag-getters";
import {
	mutateOnTagClearing,
	mutateOnTagCreated,
	mutateOnTagLoaded,
	mutateOnTagRemoved,
	mutateOnTagUpdated
} from "../imps/tag-mutations";

const state = {
	tags: new Map()
};

const getters = {
	tagAttributeDatasource: tagAttributeDatasource,
	tagIndexes: tagIndexes
};

const actions = {
	[TAG.REQUEST.LOAD]: actOnTagLoadingRequest,
	[TAG.REQUEST.CREATE]: actOnTagCreationRequest,
	[TAG.RESPONSE.LOAD]: actOnTagLoadingResponse,
	[TAG.RESPONSE.CREATE]: actOnTagCreationResponse,
	[TAG.STORAGE.CLEAR]: actOnTagCleaning
};

const mutations = {
	[TAG.RESPONSE.LOAD]: mutateOnTagLoaded,
	[TAG.RESPONSE.CREATE]: mutateOnTagCreated,
	[TAG.RESPONSE.UPDATE]: mutateOnTagUpdated,
	[TAG.RESPONSE.REMOVE]: mutateOnTagRemoved,
	[TAG.STORAGE.CLEAR]: mutateOnTagClearing
};

export default {
	state,
	getters,
	actions,
	mutations
};
