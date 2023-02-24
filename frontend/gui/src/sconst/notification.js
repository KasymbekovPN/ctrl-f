const NOTIFICATION = {
	LEVEL: {
		SUCCESS: 'NOTIFICATION_LEVEL_SUCCESS',
		ERROR: 'NOTIFICATION_LEVEL_ERROR',
		INFO: 'NOTIFICATION_LEVEL_INFO',
		WARNING: 'NOTIFICATION_LEVEL_WARNING'
	},
	NOTIFY: 'NOTIFICATION_NOTIFY',
	CLEAR: {
		ID: 'NOTIFICATION_CLIER_ID',
		ALL: 'NOTIFICATION_CLIER_ALL'
	}
};

const successAlias = 'success';
const errorAlias = 'error';
const warningAlias = 'warning';
const infoAlias = 'info';
const defaultAlias = infoAlias;
const aliases = {
	[NOTIFICATION.LEVEL.SUCCESS]: successAlias,
	[NOTIFICATION.LEVEL.ERROR]: errorAlias,
	[NOTIFICATION.LEVEL.WARNING]: warningAlias,
	[NOTIFICATION.LEVEL.INFO]: infoAlias
};

const getAlias = (level) => {
	return aliases[level] !== undefined ? aliases[level] : defaultAlias;
};

export {
	NOTIFICATION,
	getAlias
};
