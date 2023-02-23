const NOTIFICATION = {
	SUCCESS: 'NOTIFICATION_SUCCESS',
	ERROR: 'NOTIFICATION_ERROR',
	INFO: 'NOTIFICATION_INFO',
	WARNING: 'NOTIFICATION_WARNING',
	NOTIFY: 'NOTIFICATION_NOTIFY',
	CLEAR: 'NOTIFICATION_CLIER'
};

const successAlias = 'success';
const errorAlias = 'error';
const warningAlias = 'warning';
const infoAlias = 'info';
const defaultAlias = infoAlias;
const aliases = {
	[NOTIFICATION.SUCCESS]: successAlias,
	[NOTIFICATION.ERROR]: errorAlias,
	[NOTIFICATION.WARNING]: warningAlias,
	[NOTIFICATION.INFO]: infoAlias
};

const getAlias = (level) => {
	return aliases[level] !== undefined ? aliases[level] : defaultAlias;
};

export {
	NOTIFICATION,
	getAlias
};
