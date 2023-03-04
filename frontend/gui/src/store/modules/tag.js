import { TAG } from "../../sconst/tag";
import {
	actOnTagCreated,
	actOnTagLoaded,
	actOnTagRemoved,
	actOnTagUpdated
} from "../imps/tag-actions";
import {
	tagAttributeDatasource,
	tagIndexes
} from "../imps/tag-getters";
import {
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
	[TAG.LOADED]: actOnTagLoaded,
	[TAG.CREATED]: actOnTagCreated,
	[TAG.UPDATED]: actOnTagUpdated,
	[TAG.REMOVED]: actOnTagRemoved
};

const mutations = {
	[TAG.LOADED]: mutateOnTagLoaded,
	[TAG.CREATED]: mutateOnTagCreated,
	[TAG.UPDATED]: mutateOnTagUpdated,
	[TAG.REMOVED]: mutateOnTagRemoved
};

export default {
	state,
	getters,
	actions,
	mutations
};
