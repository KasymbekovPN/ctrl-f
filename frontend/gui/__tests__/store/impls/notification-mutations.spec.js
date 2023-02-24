import {
	mutateOnNotificationClearAll,
	mutateOnNotificationClearById,
	mutateOnNotificationNotify
} from "../../../src/store/imps/notification-mutations";

describe('notification-mutations.js', () => {

	test('should check mutateOnNotificationNotify', () => {
		class Notifications {
			put(notificationId, data){
				this.notificationId = notificationId;
				this.data = data;
			}
		}

		const data = {arg: 'value'};
		const id = 1;
		let state = {
			nextId: id,
			notifications: new Notifications()
		};

		mutateOnNotificationNotify(state, data);

		expect(state.nextId).toBe(id + 1);
		expect(state.notifications.notificationId).toBe(id);
		expect(state.notifications.data).toStrictEqual(data);
	});

	test('should check mutateOnNotificationClearById', () => {
		class Notifications {
			remove(notificationId){
				this.notificationId = notificationId;
			}
		}

		const expectedId = 123;
		let state = {
			notifications: new Notifications()
		};

		mutateOnNotificationClearById(state, expectedId);

		expect(state.notifications.notificationId).toBe(expectedId);
	});

	test('should check mutateOnNotificationClearAll', () => {
		class Notifications {
			clear() {
				this.called = true;
			}
		};

		let state = {notifications: new Notifications()};
		mutateOnNotificationClearAll(state);

		expect(state.notifications.called).toBe(true);
	});
});
