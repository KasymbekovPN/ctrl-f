import { TAG } from "../../sconst/tag";
import {
	actOnTagCleaning,
	actOnTagCreationRequest,
	actOnTagCreationResponse,
	actOnTagLoadingRequest,
	actOnTagLoadingResponse,
	actOnTagSelectItem,
	actOnTagUpdatingRequest,
	actOnTagUpdatingResponse
} from "../imps/tag-actions";
import {
	tagAttributeDatasource,
	tagIndexes,
	tagSelectedId
} from "../imps/tag-getters";
import {
	mutateOnTagClearing,
	mutateOnTagCreated,
	mutateOnTagItemSelect,
	mutateOnTagLoaded,
	mutateOnTagRemoved,
	mutateOnTagUpdated
} from "../imps/tag-mutations";

const state = {
	tags: new Map()
};

const getters = {
	tagAttributeDatasource: tagAttributeDatasource,
	tagIndexes: tagIndexes,
	tagSelectedId: tagSelectedId
};

const actions = {
	[TAG.REQUEST.LOAD]: actOnTagLoadingRequest,
	[TAG.REQUEST.CREATE]: actOnTagCreationRequest,
	[TAG.REQUEST.UPDATE]: actOnTagUpdatingRequest,
	[TAG.RESPONSE.LOAD]: actOnTagLoadingResponse,
	[TAG.RESPONSE.CREATE]: actOnTagCreationResponse,
	[TAG.RESPONSE.UPDATE]: actOnTagUpdatingResponse,
	[TAG.STORAGE.CLEAR]: actOnTagCleaning,
	[TAG.SELECT.ITEM]: actOnTagSelectItem
};

const mutations = {
	[TAG.RESPONSE.LOAD]: mutateOnTagLoaded,
	[TAG.RESPONSE.CREATE]: mutateOnTagCreated,
	[TAG.RESPONSE.UPDATE]: mutateOnTagUpdated,
	[TAG.RESPONSE.REMOVE]: mutateOnTagRemoved,
	[TAG.STORAGE.CLEAR]: mutateOnTagClearing,
	[TAG.SELECT.ITEM]: mutateOnTagItemSelect
};

export default {
	state,
	getters,
	actions,
	mutations
};
