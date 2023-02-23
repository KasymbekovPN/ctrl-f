import each from "jest-each";
import {
	getAlias,
	NOTIFICATION
} from "../../src/sconst/notification";


describe('notification.js', () => {
	each([
		[undefined, 'info'],
		[NOTIFICATION.SUCCESS, 'success'],
		[NOTIFICATION.INFO, 'info'],
		[NOTIFICATION.WARNING, 'warning'],
		[NOTIFICATION.ERROR, 'error']
	]).it('when level is %s getAlias must return %s', (level, expected) => {
		expect(getAlias(level)).toBe(expected);
	})
});
