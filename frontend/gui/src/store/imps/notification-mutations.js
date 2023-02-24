
const mutateOnNotificationNotify = (state, data) => {
	state.notifications.put(state.nextId++, data);
};

const mutateOnNotificationClearById = (state, notificationId)  => {
	state.notifications.remove(notificationId);
};

const mutateOnNotificationClearAll = state => {
	state.notifications.clear();
};

export {
	mutateOnNotificationNotify,
	mutateOnNotificationClearById,
	mutateOnNotificationClearAll
};
