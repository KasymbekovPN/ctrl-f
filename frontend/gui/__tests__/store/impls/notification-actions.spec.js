import { NOTIFICATION } from "../../../src/sconst/notification";
import {
	actOnNotificationClear,
	actOnNotificationError,
	actOnNotificationInfo,
	actOnNotificationWarning
} from "../../../src/store/imps/notification-actions";

describe('notification-actions.js', () => {

	let commitResult;
	const commit = (command, data) => {
		commitResult = data !== undefined ? { command, data } : { command };
	};

	const reset = () => {
		commitResult = undefined;
	};

	const code = "some.code";
	const args = {arg: 'value'};

	test('should check actOnNotificationError', () => {
		actOnNotificationError({commit}, {code, args});

		const expected = {
			command: NOTIFICATION.NOTIFY,
			data: { level: NOTIFICATION.ERROR, code, args }
		};
		expect(commitResult).toStrictEqual(expected);
		reset();
	});

	test('should check actOnNotificationInfo', () => {
		actOnNotificationInfo({commit}, {code, args});

		const expected = {
			command: NOTIFICATION.NOTIFY,
			data: { level: NOTIFICATION.INFO, code, args }
		};
		expect(commitResult).toStrictEqual(expected);
		reset();
	});

	test('should check actOnNotificationWarning', () => {
		actOnNotificationWarning({commit}, {code, args});

		const expected = {
			command: NOTIFICATION.NOTIFY,
			data: { level: NOTIFICATION.WARNING, code, args }
		};
		expect(commitResult).toStrictEqual(expected);
		reset();
	});

	test('should check actOnNotificationClear', () => {
		const notificationId = 12345;
		actOnNotificationClear({commit}, notificationId);

		const expected = {
			command: NOTIFICATION.CLEAR,
			data: notificationId
		};
		expect(commitResult).toStrictEqual(expected);
		reset();
	});
});
