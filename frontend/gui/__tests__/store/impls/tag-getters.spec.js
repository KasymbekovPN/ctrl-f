import {
	tagAttributeDatasource,
	tagIndexes
} from "../../../src/store/imps/tag-getters";
import each from "jest-each";

describe('tag-getters.js', () => {

	const ID = 123;
	const NAME = 'some name';
	test('should check tagAttributeDatasource if state.tags is undefined', () => {
		const state = {};
		const result = tagAttributeDatasource(state)(ID, NAME);

		expect(result).toBeUndefined();
	});

	const ABSENT_ATTRIBUTE_NAME = 'absentAttr';
	const ABSENT_ID = -123;
	each([
		[ID, 'id', ID],
		[ID, 'name', NAME],
		[ID, ABSENT_ATTRIBUTE_NAME, undefined],
		[ABSENT_ID, 'id', undefined],
		[ABSENT_ID, 'name', undefined],
		[ABSENT_ID, ABSENT_ATTRIBUTE_NAME, undefined]
	]).it('should check tagAttributeDatasource if id = %s & attribute = %s, it must return %s', (id, attribute, expected) => {
		const state = {
			tags: new Map().set(ID, {id: ID, name: NAME})
		};

		const result = tagAttributeDatasource(state)(id, attribute);
		expect(result).toBe(expected);
	});

	test('should check tagIndexes-getter if state.tags is undefined', () => {
		const state = {};

		const it = tagIndexes(state);
		expect(it).toStrictEqual(new Map().keys());
	});

	test('should check tagIndexes-getter', () => {
		const tags = new Map().set(ID, {id: ID, name: NAME});
		const state = { tags };

		const it = tagIndexes(state);
		expect(it).toStrictEqual(tags.keys());
	});
});
