import { NOTIFICATION } from "../../../src/sconst/notification";
import {
	actOnNotificationClearAll,
	actOnNotificationClearById,
	actOnNotificationError,
	actOnNotificationInfo,
	actOnNotificationSuccess,
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

	test('should check actOnNotificationSuccess', () => {
		actOnNotificationSuccess({commit}, {code, args});

		const expected = {
			command: NOTIFICATION.NOTIFY,
			data: { level: NOTIFICATION.LEVEL.SUCCESS, code, args }
		};
		expect(commitResult).toStrictEqual(expected);
		reset();
	});

	test('should check actOnNotificationError', () => {
		actOnNotificationError({commit}, {code, args});

		const expected = {
			command: NOTIFICATION.NOTIFY,
			data: { level: NOTIFICATION.LEVEL.ERROR, code, args }
		};
		expect(commitResult).toStrictEqual(expected);
		reset();
	});

	test('should check actOnNotificationInfo', () => {
		actOnNotificationInfo({commit}, {code, args});

		const expected = {
			command: NOTIFICATION.NOTIFY,
			data: { level: NOTIFICATION.LEVEL.INFO, code, args }
		};
		expect(commitResult).toStrictEqual(expected);
		reset();
	});

	test('should check actOnNotificationWarning', () => {
		actOnNotificationWarning({commit}, {code, args});

		const expected = {
			command: NOTIFICATION.NOTIFY,
			data: { level: NOTIFICATION.LEVEL.WARNING, code, args }
		};
		expect(commitResult).toStrictEqual(expected);
		reset();
	});

	test('should check actOnNotificationClearById', () => {
		const notificationId = 12345;
		actOnNotificationClearById({commit}, notificationId);

		const expected = {
			command: NOTIFICATION.CLEAR.ID,
			data: notificationId
		};
		expect(commitResult).toStrictEqual(expected);
		reset();
	});

	test('should check actOnNotificationClearAll', () => {
		actOnNotificationClearAll({commit});

		const expected = { command: NOTIFICATION.CLEAR.ALL };
		expect(commitResult).toStrictEqual(expected);
		reset();
	});
});
