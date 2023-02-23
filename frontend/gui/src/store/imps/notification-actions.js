import { NOTIFICATION } from "../../sconst/notification";

const actOnNotificationSuccess = ({commit}, {code, args}) => {
	commit(NOTIFICATION.NOTIFY, {code, args, level: NOTIFICATION.SUCCESS});
};

const actOnNotificationError = ({commit}, {code, args}) => {
	commit(NOTIFICATION.NOTIFY, {code, args, level: NOTIFICATION.ERROR});
};

const actOnNotificationInfo = ({commit}, {code, args}) => {
	commit(NOTIFICATION.NOTIFY, {code, args, level: NOTIFICATION.INFO});
};

const actOnNotificationWarning = ({commit}, {code, args}) => {
	commit(NOTIFICATION.NOTIFY, {code, args, level: NOTIFICATION.WARNING});
};

const actOnNotificationClear = ({commit}, notificationId) => {
	commit(NOTIFICATION.CLEAR, notificationId);
};

export {
	actOnNotificationSuccess,
	actOnNotificationError,
	actOnNotificationInfo,
	actOnNotificationWarning,
	actOnNotificationClear
};
