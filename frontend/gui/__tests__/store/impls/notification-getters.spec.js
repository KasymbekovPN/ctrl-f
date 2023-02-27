import {
	getNotifications,
	isNotificationVisible
} from "../../../src/store/imps/notification-getters";

describe('auth-getters.js', () => {

	test('should check getNotifications if state.notification is undefined', () => {
		const state = {};
		const result = getNotifications(state);
		expect(result.length).toBe(0);
	});

	test('should check getNotifications', () => {
		const expected = [
			{id: 0},
			{id: 1}
		];

		class Notifications {
			get() {return expected};
		}

		const state = {notifications: new Notifications()};
		const result = getNotifications(state);
		expect(result).toBe(expected);
	});

	test('should check isNotificationVisible-getter if state.notifications is undefined', () => {
		const state = {};
		const result = isNotificationVisible(state);
		expect(result).toBe(false);
	});

	test('should check isNotificationVisible-getter if state.notifications has not isEmpty-method', () => {
		const state = {notifications: {}};
		const result = isNotificationVisible(state);
		expect(result).toBe(false);
	});

	test('should check isNotificationVisible-getter if state.notifications is empty', () => {
		class Notifications{
			isEmpty() { return true; }
		}

		const state = { notifications: new Notifications() };
		const result = isNotificationVisible(state);
		expect(result).toBe(false);
	});

	test('should check isNotificationVisible-getter if state.notifications is not empty', () => {
		class Notifications{
			isEmpty() { return false; }
		}

		const state = { notifications: new Notifications() };
		const result = isNotificationVisible(state);
		expect(result).toBe(true);
	});
});
