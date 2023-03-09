import config from "../../../config";
import {
	processAuthRequestSubscription,
	processClientParamsSubscription,
	processI18nSubscription,
	processLogoutRequestSubscription,
	processTagCreationSubscription,
	processTagLoadingSubscription,
	processTagRemovingSubscription,
	processTagUpdatingSubscription
} from "../../../src/store/imps/subscription-actions";
import { CONNECTION } from "../../../src/sconst/connection";
import { I18N } from "../../../src/sconst/i18n";
import { AUTH } from "../../../src/sconst/auth";
import { TAG } from "../../../src/sconst/tag";
import { NOTIFICATION } from "../../../src/sconst/notification";
import testDispatch from "../../../__utils/test-dispatch";

describe('subscription-actions.js', () => {

	const response = {
		body: '{\"value\": {\"locale\":\"en\"}}'
	};

	test('should check clientParams subscription action ', () => {
		const clientParams = JSON.parse(response.body);
		const expectedDispatchResult = [
			{command: I18N.SET.LOCALE, data: clientParams.value.locale},
			{
				command: CONNECTION.SEND,
				data: {
					destination: config.requests.i18n,
					headers: {},
					body: {}
				}
			}
		];

		processClientParamsSubscription({dispatch: testDispatch.dispatch}, response);
		expect(testDispatch.getResult()).toStrictEqual(expectedDispatchResult);
	});

	test('should check i18n subscription action ', () => {
		const data = JSON.parse(response.body);
		const expectedDispatchResult = [{
			command: I18N.SET.TEMPLATES,
			data
		}];

		processI18nSubscription({dispatch: testDispatch.dispatch}, response);
		expect(testDispatch.getResult()).toStrictEqual(expectedDispatchResult);
	});

	test('should check authRequest subscription action ', () => {
		const data = JSON.parse(response.body);
		const expectedDispatchResult = [{
			command: AUTH.LOGIN.RESPONSE,
			data
		}];

		processAuthRequestSubscription({dispatch: testDispatch.dispatch}, response);
		expect(testDispatch.getResult()).toStrictEqual(expectedDispatchResult);
	});

	test('should check logoutRequest subscription action ', () => {
		const data = JSON.parse(response.body);
		const expectedDispatchResult = [{
			command: AUTH.LOGOUT.RESPONSE,
			data
		}];

		processLogoutRequestSubscription({dispatch: testDispatch.dispatch}, response);
		expect(testDispatch.getResult()).toStrictEqual(expectedDispatchResult);
	});

	test('should check tag loalding if success', () => {
		const tag = {id: 1, name: 'some.name'};
		const tags = [tag];
		const expectedDispatchResult = [{
			command: TAG.RESPONSE.LOAD,
			data: tags
		}];
		const body = {
			success: true,
			value: {tags}
		};

		const response = {body: JSON.stringify(body)};
		processTagLoadingSubscription({dispatch: testDispatch.dispatch}, response);
		expect(testDispatch.getResult()).toStrictEqual(expectedDispatchResult);
	});

	test('should check tag loalding if fail', () => {
		const code = 'some.code';
		const args = {arg: 'some,.arg'}
		const expectedDispatchResult = [{
			command: NOTIFICATION.LEVEL.ERROR,
			data: {code, args}
		}];
		const body = { success: false, code, args };

		const response = {body: JSON.stringify(body)};
		processTagLoadingSubscription({dispatch: testDispatch.dispatch}, response);
		expect(testDispatch.getResult()).toStrictEqual(expectedDispatchResult);
	});

	test('should check tag creation subscription action if success', () => {
		const value = {id: 1, name: "name"};
		const expectedDispatchResult = [{
			command: TAG.RESPONSE.CREATE,
			data: value
		}];
		const body = {
			success: true,
			value
		};
		const response = {
			body: JSON.stringify(body)
		};

		processTagCreationSubscription({dispatch: testDispatch.dispatch}, response);
		expect(testDispatch.getResult()).toStrictEqual(expectedDispatchResult);
	})

	test('should check tag creation subscription action if fail', () => {
		const code = 'some.code';
		const args = {arg0: 'value0'};
		const expectedDispatchResult = [{
			command: NOTIFICATION.LEVEL.ERROR,
			data: {code, args}
		}];
		const body = {
			success: false,
			code,
			args
		};
		const response = {
			body: JSON.stringify(body)
		};

		processTagCreationSubscription({dispatch: testDispatch.dispatch}, response);
		expect(testDispatch.getResult()).toStrictEqual(expectedDispatchResult);
	})

	test('should check tag updating subscription action if success', () => {
		const value = {id: 1, name: "name"};
		const expectedDispatchResult = [{
			command: TAG.RESPONSE.UPDATE,
			data: value
		}];
		const body = {
			success: true,
			value
		};
		const response = {
			body: JSON.stringify(body)
		};

		processTagUpdatingSubscription({dispatch: testDispatch.dispatch}, response);
		expect(testDispatch.getResult()).toStrictEqual(expectedDispatchResult);
	})

	test('should check tag updating subscription action if fail', () => {
		const code = 'some.code';
		const args = {arg0: 'value0'};
		const expectedDispatchResult = [{
			command: NOTIFICATION.LEVEL.ERROR,
			data: {code, args}
		}];
		const body = {
			success: false,
			code,
			args
		};
		const response = {
			body: JSON.stringify(body)
		};

		processTagUpdatingSubscription({dispatch: testDispatch.dispatch}, response);
		expect(testDispatch.getResult()).toStrictEqual(expectedDispatchResult);
	})

	test('should check tag removing subscription action if success', () => {
		const value = {id: 1};
		const expectedDispatchResult = [{
			command: TAG.RESPONSE.REMOVE,
			data: value.id
		}];
		const body = { success: true, value };
		const response = { body: JSON.stringify(body) };

		processTagRemovingSubscription({dispatch: testDispatch.dispatch}, response);
		expect(testDispatch.getResult()).toStrictEqual(expectedDispatchResult);
	})

	test('should check tag removing subscription action if fail', () => {
		const code = 'some.code';
		const args = {arg0: 'value0'};
		const expectedDispatchResult = [{
			command: NOTIFICATION.LEVEL.ERROR,
			data: {code, args}
		}];
		const body = { success: false, code, args };
		const response = { body: JSON.stringify(body) };

		processTagRemovingSubscription({dispatch: testDispatch.dispatch}, response);
		expect(testDispatch.getResult()).toStrictEqual(expectedDispatchResult);
	})
});
