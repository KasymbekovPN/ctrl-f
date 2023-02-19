
const isNotificationVisible = state => {
	return !(state.notifications === undefined || state.notifications.isEmpty());
};

const getNotifications = state => {
	return state.notifications !== undefined ? state.notifications.get() : [];
};

export {
	isNotificationVisible,
	getNotifications
};
