import { TAG } from "../../sconst/tag";

const actOnTagLoaded = ({commit}, tags) => { commit(TAG.LOADED, tags); };

const actOnTagCreated = ({commit}, tag) => { commit(TAG.CREATED, tag); };

const actOnTagUpdated = ({commit}, tag) => { commit(TAG.UPDATED, tag); };

const actOnTagRemoved = ({commit}, id) => { commit(TAG.REMOVED, id); };

export {
	actOnTagLoaded,
	actOnTagCreated,
	actOnTagUpdated,
	actOnTagRemoved
};
