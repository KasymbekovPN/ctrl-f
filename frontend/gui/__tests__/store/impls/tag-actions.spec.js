import { TAG } from "../../../src/sconst/tag";
import {
	actOnTagCreated,
	actOnTagLoaded,
	actOnTagRemoved,
	actOnTagUpdated
} from "../../../src/store/imps/tag-actions";

describe('tag-actions.js', () => {

	const id = 123;
	const name = 'some.name';

	let commitResult;
	const commit = (command, data) => {
		commitResult = data !== undefined ? { command, data } : { command };
	};

	afterEach(() => {
		commitResult = undefined;
	});

	test('should check actOnTagLoaded', () => {
		const tags = [{ id, name }];
		const expected = {
			command: TAG.LOADED,
			data: tags
		};

		actOnTagLoaded({commit}, tags);
		expect(commitResult).toStrictEqual(expected);
	});

	test('should check actOnTagCreated', () => {
		const tag = { id, name };
		const expected = {
			command: TAG.CREATED,
			data: tag
		};

		actOnTagCreated({commit}, tag);
		expect(commitResult).toStrictEqual(expected);
	});

	test('should check actOnTagUpdated', () => {
		const tag = { id, name };
		const expected = {
			command: TAG.UPDATED,
			data: tag
		};

		actOnTagUpdated({commit}, tag);
		expect(commitResult).toStrictEqual(expected);
	});

	test('should check actOnTagRemoved', () => {
		const expected = {
			command: TAG.REMOVED,
			data: id
		};

		actOnTagRemoved({commit}, id);
		expect(commitResult).toStrictEqual(expected);
	});
});
