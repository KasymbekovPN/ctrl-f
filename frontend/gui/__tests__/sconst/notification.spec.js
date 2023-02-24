import each from "jest-each";
import {
	getAlias,
	NOTIFICATION
} from "../../src/sconst/notification";


describe('notification.js', () => {
	each([
		[undefined, 'info'],
		[NOTIFICATION.LEVEL.SUCCESS, 'success'],
		[NOTIFICATION.LEVEL.INFO, 'info'],
		[NOTIFICATION.LEVEL.WARNING, 'warning'],
		[NOTIFICATION.LEVEL.ERROR, 'error']
	]).it('when level is %s getAlias must return %s', (level, expected) => {
		expect(getAlias(level)).toBe(expected);
	})
});
