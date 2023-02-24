import config from '../../../config';
import Notifications from "../..//notification/notifications";
import { NOTIFICATION } from "../../sconst/notification";
import {
	actOnNotificationClearAll,
	actOnNotificationClearById,
	actOnNotificationError,
	actOnNotificationInfo,
	actOnNotificationSuccess,
	actOnNotificationWarning
} from "../imps/notification-actions";
import {
	getNotifications
} from "../imps/notification-getters";
import {
	mutateOnNotificationClearAll,
	mutateOnNotificationClearById,
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
	[NOTIFICATION.LEVEL.SUCCESS]: actOnNotificationSuccess,
	[NOTIFICATION.LEVEL.ERROR]: actOnNotificationError,
	[NOTIFICATION.LEVEL.INFO]: actOnNotificationInfo,
	[NOTIFICATION.LEVEL.WARNING]: actOnNotificationWarning,
	[NOTIFICATION.CLEAR.ID]: actOnNotificationClearById,
	[NOTIFICATION.CLEAR.ALL]: actOnNotificationClearAll
};

const mutations = {
	[NOTIFICATION.NOTIFY]: mutateOnNotificationNotify,
	[NOTIFICATION.CLEAR.ID]: mutateOnNotificationClearById,
	[NOTIFICATION.CLEAR.ALL]: mutateOnNotificationClearAll
};

export default {
	state,
	getters,
	actions,
	mutations
};
