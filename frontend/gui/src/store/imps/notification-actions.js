import { NOTIFICATION } from "../../sconst/notification";

const actOnNotificationSuccess = ({commit}, {code, args}) => {
	commit(NOTIFICATION.NOTIFY, {code, args, level: NOTIFICATION.LEVEL.SUCCESS});
};

const actOnNotificationError = ({commit}, {code, args}) => {
	commit(NOTIFICATION.NOTIFY, {code, args, level: NOTIFICATION.LEVEL.ERROR});
};

const actOnNotificationInfo = ({commit}, {code, args}) => {
	commit(NOTIFICATION.NOTIFY, {code, args, level: NOTIFICATION.LEVEL.INFO});
};

const actOnNotificationWarning = ({commit}, {code, args}) => {
	commit(NOTIFICATION.NOTIFY, {code, args, level: NOTIFICATION.LEVEL.WARNING});
};

const actOnNotificationClearById = ({commit}, notificationId) => {
	commit(NOTIFICATION.CLEAR.ID, notificationId);
};

const actOnNotificationClearAll = ({commit}) => {
	commit(NOTIFICATION.CLEAR.ALL);
};

export {
	actOnNotificationSuccess,
	actOnNotificationError,
	actOnNotificationInfo,
	actOnNotificationWarning,
	actOnNotificationClearById,
	actOnNotificationClearAll
};
