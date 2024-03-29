import { TAG } from "./tag";

export const DOMAIN_MANAGER = {
	AFTER: {
		LOGIN: 'DOMAIN_MANAGER_AFTER_LOGIN',
		LOGOUT: 'DOMAIN_MANAGER_AFTER_LOGOUT'
	},
	LOAD_REQUESTS: [
		TAG.REQUEST.LOAD
	],
	REMOVE_REQUESTS: [
		TAG.STORAGE.CLEAR
	]
};
