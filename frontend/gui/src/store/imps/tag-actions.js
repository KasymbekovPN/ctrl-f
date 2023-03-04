import { TAG } from "../../sconst/tag";

const actOnTagLoaded = ({commit}, tags) => {
	//<
	console.log(`act: ${TAG.LOADED}`);
	console.log(tags);
	//<
	commit(TAG.LOADED, tags);
};

const actOnTagCreated = ({commit}, tag) => {
	//<
	console.log(`act: ${TAG.CREATED}`);
	console.log(tag);
	//<
	commit(TAG.CREATED, tag);
};

const actOnTagUpdated = ({commit}, tag) => {
	//<
	console.log(`act: ${TAG.UPDATED}`);
	console.log(tag);
	//<
	commit(TAG.UPDATED, tag);
};

const actOnTagRemoved = ({commit}, id) => {
	//<
	console.log(`act: ${TAG.REMOVED}`);
	console.log(id);
	//<
	commit(TAG.REMOVED, id);
};

export {
	actOnTagLoaded,
	actOnTagCreated,
	actOnTagUpdated,
	actOnTagRemoved
};
