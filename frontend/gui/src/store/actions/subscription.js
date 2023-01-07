const SUBSCRIPTION_CLIENT_PARAMS = "SUBSCRIPTION_CLIENT_PARAMS";
const SUBSCRIPTION_I18N = "SUBSCRIPTION_I18N";

const SUBSCRIPTIONS = {
	[SUBSCRIPTION_CLIENT_PARAMS]: '/topic/clientParamsResponse/',
	[SUBSCRIPTION_I18N]: '/topic/i18nResponse/'
};

export {
	SUBSCRIPTIONS,
	SUBSCRIPTION_CLIENT_PARAMS,
	SUBSCRIPTION_I18N
};