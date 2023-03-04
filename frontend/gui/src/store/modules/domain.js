import { DOMAIN } from "../../sconst/domain";
import {
	actOnTagLoadingRequest,
	processTagCreationRequest,
	processTagDeletingRequest,
	processTagUpdatingRequest
} from "../imps/domain-tag-actions";

const state = {};

const getters = {};

const actions = {
	[DOMAIN.TAG.REQUEST.LOAD]: actOnTagLoadingRequest,
	[DOMAIN.TAG.REQUEST.CREATE]: processTagCreationRequest,
	[DOMAIN.TAG.REQUEST.UPDATE]: processTagUpdatingRequest,
	[DOMAIN.TAG.REQUEST.DELETE]: processTagDeletingRequest
};

const mutations = {};

export default {
    state,
    getters,
    actions,
    mutations
};
