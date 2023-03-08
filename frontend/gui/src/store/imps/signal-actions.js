import config from "../../../config";
import { SIGNAL } from "../../sconst/signal";

const actOnSomeModalShow = ({commit}, route) => {
	commit(SIGNAL.MODAL.SOME.ADD.SHOW, route);
};

const actOnSomeModalHide = ({commit}, route) => {
	commit(SIGNAL.MODAL.SOME.ADD.HIDE, route);
};

const actOnTagModalShow = ({commit}) => {
	commit(SIGNAL.MODAL.SOME.ADD.SHOW, config.paths.tags);
};

const actOnTagModalHide = ({commit}) => {
	commit(SIGNAL.MODAL.SOME.ADD.HIDE, config.paths.tags);
};

export {
	actOnSomeModalShow,
	actOnSomeModalHide,
	actOnTagModalShow,
	actOnTagModalHide
};
