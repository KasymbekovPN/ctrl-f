import {
	getNotifications
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
});
