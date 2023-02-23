import config from '../../../config';
import Notifications from "../..//notification/notifications";
import { NOTIFICATION } from "../../sconst/notification";
import {
	actOnNotificationClear,
	actOnNotificationError,
	actOnNotificationInfo,
	actOnNotificationSuccess,
	actOnNotificationWarning
} from "../imps/notification-actions";
import {
	getNotifications
} from "../imps/notification-getters";
import {
	mutateOnNotificationClear,
	mutateOnNotificationNotify
} from "../imps/notification-mutations";

const state = {
	notifications: new Notifications(config.notification.delay, config.notification.size),
	nextId: Number.MIN_SAFE_INTEGER
};

const getters = {
	getNotifications: getNotifications
};

const actions = {
	[NOTIFICATION.SUCCESS]: actOnNotificationSuccess,
	[NOTIFICATION.ERROR]: actOnNotificationError,
	[NOTIFICATION.INFO]: actOnNotificationInfo,
	[NOTIFICATION.WARNING]: actOnNotificationWarning,
	[NOTIFICATION.CLEAR]: actOnNotificationClear
};

const mutations = {
	[NOTIFICATION.NOTIFY]: mutateOnNotificationNotify,
	[NOTIFICATION.CLEAR]: mutateOnNotificationClear
};

export default {
	state,
	getters,
	actions,
	mutations
};
