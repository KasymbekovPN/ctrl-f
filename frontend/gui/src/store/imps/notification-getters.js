
const isNotificationVisible = state => {
	return  state.notifications !== undefined &&
			typeof state.notifications.isEmpty === 'function' &&
			!state.notifications.isEmpty();
};

const getNotifications = state => {
	return state.notifications !== undefined ? state.notifications.get() : [];
};

export {
	getNotifications,
	isNotificationVisible
};
