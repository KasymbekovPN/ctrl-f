import Notifications from "../../src/notification/notifications";

describe('notifications.js', () => {

	const delay = 50;
	const size = 5;

	test('should check put if data is not contained', () => {
		const notificationId = 1;
		const notification = { arg: 'value' };
		const expected = new Map();
		expected.set(notificationId, notification);

		const notifications = new Notifications(delay, size);
		notifications.put(notificationId, notification);

		expect(notifications._data).toStrictEqual(expected);
		expect(notifications._timerIds.size).toBe(1);
	});

	test('should check put if data is contained', () => {
		const notificationId = 1;
		const firstNotification = { arg: 'first' };
		const secondNotification = { arg: 'second' };
		const expected = new Map();
		expected.set(notificationId, firstNotification);

		const notifications = new Notifications(delay, size);
		notifications.put(notificationId, firstNotification);
		notifications.put(notificationId, secondNotification);

		expect(notifications._data).toStrictEqual(expected);
	});

	test('should check get if data is empty', () => {
		const notifications = new Notifications(delay, size);

		expect(notifications.get().length).toBe(0);
	});

	test('should check get if data is not empty', () => {
		const firstNotificationId = 0;
		const secondNotificationId = 1;
		const firstNotification = { arg: 'first' };
		const secondNotification = { arg: 'second' };
		const expected = [
			Object.assign({id: firstNotificationId}, firstNotification),
			Object.assign({id: secondNotificationId}, secondNotification)
		];

		const notifications = new Notifications(delay, size);
		notifications.put(firstNotificationId, firstNotification);
		notifications.put(secondNotificationId, secondNotification);

		expect(notifications.get()).toStrictEqual(expected);
	});

	test('should check remove if data is empty', () => {
		const notifications = new Notifications(delay, size);
		notifications.remove(0);

		expect(notifications._data.size).toBe(0);
		expect(notifications._timerIds.size).toBe(0);
	});

	test('should check remove if data does not contain notification', () => {
		const firstNotificationId = 0;
		const secondNotificationId = 1;
		const firstNotification = { arg: 'first' };
		const expected = new Map();
		expected.set(firstNotificationId, firstNotification);

		const notifications = new Notifications(delay, size);
		notifications.put(firstNotificationId, firstNotification);
		notifications.remove(secondNotificationId);

		expect(notifications._data).toStrictEqual(expected);
		expect(notifications._timerIds.size).toBe(1);
	});

	test('should check remove if data does contains notification', () => {
		const firstNotificationId = 0;
		const secondNotificationId = 1;
		const firstNotification = { arg: 'first' };
		const secondNotification = { arg: 'second' };
		const expected = new Map();
		expected.set(firstNotificationId, firstNotification);

		const notifications = new Notifications(delay, size);
		notifications.put(firstNotificationId, firstNotification);
		notifications.put(secondNotificationId, secondNotification);
		expect(notifications._data.size).toBe(2);
		expect(notifications._timerIds.size).toBe(2);

		notifications.remove(secondNotificationId);

		expect(notifications._data).toStrictEqual(expected);
		expect(notifications._timerIds.size).toBe(1);
	});

	test('should check isEmpty if it is empty', () => {
		const notifications = new Notifications(delay, size);

		expect(notifications.isEmpty()).toBe(true);
	});

	test('should check isEmpty if it is not empty', () => {
		const firstNotificationId = 0;
		const firstNotification = { arg: 'first' };

		const notifications = new Notifications(delay, size);
		notifications.put(firstNotificationId, firstNotification);

		expect(notifications.isEmpty()).toBe(false);
	});

	test('should check autoremoving', () => {
		const firstNotificationId = 0;
		const firstNotification = { arg: 'first' };

		const notifications = new Notifications(delay, size);
		notifications.put(firstNotificationId, firstNotification);

		expect(notifications._data.size).toBe(1);
		expect(notifications._timerIds.size).toBe(1);

		setTimeout(() => {
			expect(notifications._data.size).toBe(0);
			expect(notifications._timerIds.size).toBe(0);
		}, 100);
	});
});
