import config from "../../config";
import { TAG } from "./tag";

export const DOMAIN_MANAGER = {
	AFTER: {
		LOGIN: 'DOMAIN_MANAGER_AFTER_LOGIN',
		LOGOUT: 'DOMAIN_MANAGER_AFTER_LOGOUT'
	},
	LOAD_REQUESTS: [
		config.requests.tag.load
	],
	REMOVE_REQUESTS: [
		TAG.REMOVED_ALL
	]
};
