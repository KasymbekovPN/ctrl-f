
const getNotifications = state => {
	return state.notifications !== undefined ? state.notifications.get() : [];
};

export {
	getNotifications
};
