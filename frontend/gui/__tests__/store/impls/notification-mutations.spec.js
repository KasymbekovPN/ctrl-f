import { mutateOnNotificationClear, mutateOnNotificationNotify } from "../../../src/store/imps/notification-mutations";

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

	test('should check mutateOnNotificationClear', () => {
		class Notifications {
			remove(notificationId){
				this.notificationId = notificationId;
			}
		}

		const expectedId = 123;
		let state = {
			notifications: new Notifications()
		};

		mutateOnNotificationClear(state, expectedId);

		expect(state.notifications.notificationId).toBe(expectedId);
	});
});
