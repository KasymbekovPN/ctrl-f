import { TAG } from "../../sconst/tag";
import config from "../../../config";
import { AUTH } from "../../sconst/auth";
import { CONNECTION } from "../../sconst/connection";
import { I18N } from "../../sconst/i18n";
import { NOTIFICATION } from "../../sconst/notification";

const processClientParamsSubscription = ({dispatch}, response) => {
	const clientParams = JSON.parse(response.body);
	dispatch(I18N.SET.LOCALE, clientParams.value.locale);
	dispatch(CONNECTION.SEND, {
		destination: config.requests.i18n,
		headers: {},
		body: {}
	});
};

const processI18nSubscription = ({dispatch}, response) => {
	const data = JSON.parse(response.body);
	dispatch(I18N.SET.TEMPLATES, data);
};

const processAuthRequestSubscription = ({dispatch}, response) => {
	const data = JSON.parse(response.body);
	dispatch(AUTH.LOGIN.RESPONSE, data);
};

const processLogoutRequestSubscription = ({dispatch}, response) => {
	const data = JSON.parse(response.body);
	dispatch(AUTH.LOGOUT.RESPONSE, data);
};

const processTagLoadingSubscription = ({dispatch}, response) => {
	const data = JSON.parse(response.body);
	if (data.success){
		dispatch(TAG.RESPONSE.LOAD, data.value.tags);
	} else {
		const {code, args} = data;
		dispatch(NOTIFICATION.LEVEL.ERROR, {code, args});
	}
};

const processTagCreationSubscription = ({dispatch}, response) => {
	const data = JSON.parse(response.body);
	if (data.success){
		dispatch(TAG.RESPONSE.CREATE, data.value);
	} else {
		const {code, args} = data;
		dispatch(NOTIFICATION.LEVEL.ERROR, {code, args});
	}
};

const processTagUpdatingSubscription = ({dispatch}, response) => {
	const data = JSON.parse(response.body);
	if (data.success){
		dispatch(TAG.RESPONSE.UPDATE, data.value);
	} else {
		const {code, args} = data;
		dispatch(NOTIFICATION.LEVEL.ERROR, {code, args});
	}
};

const processTagRemovingSubscription = ({dispatch}, response) => {
	const data = JSON.parse(response.body);
	if (data.success){
		dispatch(TAG.RESPONSE.REMOVE, data.value.id);
	} else {
		const {code, args} = data;
		dispatch(NOTIFICATION.LEVEL.ERROR, {code, args});
	}
};

export {
	processClientParamsSubscription,
	processI18nSubscription,
	processAuthRequestSubscription,
	processLogoutRequestSubscription,
	processTagLoadingSubscription,
	processTagCreationSubscription,
	processTagUpdatingSubscription,
	processTagRemovingSubscription
};
