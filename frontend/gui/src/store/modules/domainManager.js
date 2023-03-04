import { DOMAIN_MANAGER } from "../../sconst/domainManager";
import {
	actDomainManagerAfterLogin,
	actDomainManagerAfterLogout
} from "../imps/domainManager-action";

const state = {};

const getters = {};

const actions = {
	[DOMAIN_MANAGER.AFTER.LOGIN]: actDomainManagerAfterLogin,
	[DOMAIN_MANAGER.AFTER.LOGOUT]: actDomainManagerAfterLogout
};

const mutations = {};

export default {
    state,
    getters,
    actions,
    mutations
};
