
const isNotificationVisible = state => {
	// return !(state.notifications === undefined || state.notifications.isEmpty());
	//<
	console.log(state);
	return true;
};

const getNotifications = state => {
	return state.notifications !== undefined ? state.notifications.get() : [];
};

export {
	isNotificationVisible,
	getNotifications
};
