import { NOTIFICATION } from "../../../src/sconst/notification";
import {
	actOnNotificationClearAll,
	actOnNotificationClearById,
	actOnNotificationError,
	actOnNotificationInfo,
	actOnNotificationSuccess,
	actOnNotificationWarning
} from "../../../src/store/imps/notification-actions";
import testCommit from "../../../__utils/test-commit";

describe('notification-actions.js', () => {

	const code = "some.code";
	const args = {arg: 'value'};

	test('should check actOnNotificationSuccess', () => {
		actOnNotificationSuccess({commit: testCommit.commit}, {code, args});

		const expected = [{
			command: NOTIFICATION.NOTIFY,
			data: { level: NOTIFICATION.LEVEL.SUCCESS, code, args }
		}];
		expect(testCommit.getResult()).toStrictEqual(expected);
	});

	test('should check actOnNotificationError', () => {
		actOnNotificationError({commit: testCommit.commit}, {code, args});

		const expected = [{
			command: NOTIFICATION.NOTIFY,
			data: { level: NOTIFICATION.LEVEL.ERROR, code, args }
		}];
		expect(testCommit.getResult()).toStrictEqual(expected);
	});

	test('should check actOnNotificationInfo', () => {
		actOnNotificationInfo({commit: testCommit.commit}, {code, args});

		const expected = [{
			command: NOTIFICATION.NOTIFY,
			data: { level: NOTIFICATION.LEVEL.INFO, code, args }
		}];
		expect(testCommit.getResult()).toStrictEqual(expected);
	});

	test('should check actOnNotificationWarning', () => {
		actOnNotificationWarning({commit: testCommit.commit}, {code, args});

		const expected = [{
			command: NOTIFICATION.NOTIFY,
			data: { level: NOTIFICATION.LEVEL.WARNING, code, args }
		}];
		expect(testCommit.getResult()).toStrictEqual(expected);
	});

	test('should check actOnNotificationClearById', () => {
		const notificationId = 12345;
		actOnNotificationClearById({commit: testCommit.commit}, notificationId);

		const expected = [{
			command: NOTIFICATION.CLEAR.ID,
			data: notificationId
		}];
		expect(testCommit.getResult()).toStrictEqual(expected);
	});

	test('should check actOnNotificationClearAll', () => {
		actOnNotificationClearAll({commit: testCommit.commit});

		const expected = [{ command: NOTIFICATION.CLEAR.ALL }];
		expect(testCommit.getResult()).toStrictEqual(expected);
	});
});
