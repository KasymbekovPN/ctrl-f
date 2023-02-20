
const mutateOnNotificationNotify = (state, data) => {
	state.notifications.put(state.nextId++, data);
};

const mutateOnNotificationClear = (state, notificationId)  => {
	state.notifications.remove(notificationId);
};

export {
	mutateOnNotificationNotify,
	mutateOnNotificationClear
};
