export const NOTIFICATION = {
	ERROR: 'NOTIFICATION_ERROR',
	INFO: 'NOTIFICATION_INFO',
	WARNING: 'NOTIFICATION_WARNING',
	NOTIFY: 'NOTIFICATION_NOTIFY',
	CLEAR: 'NOTIFICATION_CLIER'
};

const errorIcon = 'error';
const warningIcon = 'warning';
const infoIcon = 'info';
const defaultIcon = infoIcon;
const icons = {
	[NOTIFICATION.ERROR]: errorIcon,
	[NOTIFICATION.WARNING]: warningIcon,
	[NOTIFICATION.INFO]: infoIcon
};

export const getIcon = (level) => {
	return icons[level] !== undefined ? icons[level] : defaultIcon;
};
