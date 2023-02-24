import config from "../../../config";
import {
	processAuthRequestSubscription,
	processClientParamsSubscription,
	processI18nSubscription,
	processLogoutRequestSubscription,
	processTagCreationSubscription
} from "../../../src/store/imps/subscription-actions";
import { CONNECTION } from "../../../src/sconst/connection";
import { I18N } from "../../../src/sconst/i18n";
import { AUTH } from "../../../src/sconst/auth";
import { TAG } from "../../../src/sconst/tag";
import { NOTIFICATION } from "../../../src/sconst/notification";

describe('subscription-actions.js', () => {

	const response = {
		body: '{\"value\": {\"locale\":\"en\"}}'
	};

	let dispatchResult = {};
	const dispatch = (command, data) => {
		dispatchResult[command] = data;
	};

	const reset = () => {
		dispatchResult = {};
	};

	test('should check clientParams subscription action ', () => {
		const clientParams = JSON.parse(response.body);
		const expectedDispatchResult = {
			[I18N.SET.LOCALE]: clientParams.value.locale,
			[CONNECTION.SEND]: {
				destination: config.requests.i18n,
				headers: {},
				body: {}
			}
		};

		processClientParamsSubscription({dispatch}, response);
		expect(dispatchResult).toStrictEqual(expectedDispatchResult);
		reset();
	});

	test('should check i18n subscription action ', () => {
		const data = JSON.parse(response.body);
		const expectedDispatchResult = {
			[I18N.SET.TEMPLATES]: data
		};

		processI18nSubscription({dispatch}, response);
		expect(dispatchResult).toStrictEqual(expectedDispatchResult);
		reset();
	});

	test('should check authRequest subscription action ', () => {
		const data = JSON.parse(response.body);
		const expectedDispatchResult = {
			[AUTH.LOGIN.RESPONSE]: data
		};

		processAuthRequestSubscription({dispatch}, response);
		expect(dispatchResult).toStrictEqual(expectedDispatchResult);
		reset();
	});

	test('should check logoutRequest subscription action ', () => {
		const data = JSON.parse(response.body);
		const expectedDispatchResult = {
			[AUTH.LOGOUT.RESPONSE]: data
		};

		processLogoutRequestSubscription({dispatch}, response);
		expect(dispatchResult).toStrictEqual(expectedDispatchResult);
		reset();
	});

	test('should check tag creation subscription action if success', () => {
		const value = {id: 1, name: "name"};
		const expectedDispatchResult = {
			[TAG.CREATED]: value
		};
		const body = {
			success: true,
			value
		};
		const response = {
			body: JSON.stringify(body)
		};

		processTagCreationSubscription({dispatch}, response);
		expect(dispatchResult).toStrictEqual(expectedDispatchResult);
		reset();
	})

	test('should check tag creation subscription action if fail', () => {
		const code = 'some.code';
		const args = {arg0: 'value0'};
		const expectedDispatchResult = {
			[NOTIFICATION.LEVEL.ERROR]: {code, args}
		};
		const body = {
			success: false,
			code,
			args
		};
		const response = {
			body: JSON.stringify(body)
		};

		processTagCreationSubscription({dispatch}, response);
		expect(dispatchResult).toStrictEqual(expectedDispatchResult);
		reset();
	})
});
