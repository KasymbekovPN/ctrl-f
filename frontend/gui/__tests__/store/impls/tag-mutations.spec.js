import {
	mutateOnTagClearing,
	mutateOnTagCreated,
	mutateOnTagItemSelect,
	mutateOnTagLoaded,
	mutateOnTagRemoved,
	mutateOnTagUpdated
} from "../../../src/store/imps/tag-mutations";

describe('tag-mutations.js', () => {

	const id = 123;
	const name = 'some.name';

	test('should check mutateOnTagLoaded if state.tags is empty', () => {
		const tag = { id, name };
		const tags = [tag];
		const expectedState = { tags: new Map().set(id, tag) };

		let state = {tags: new Map()};
		mutateOnTagLoaded(state, tags);
		expect(state).toStrictEqual(expectedState);
	});

	test('should check mutateOnTagLoaded is not empty', () => {
		const tag = { id, name };
		const tags = [tag];
		const expectedState = { tags: new Map().set(id, tag) };

		let state = { tags: new Map().set(0, {id: 0, name: 'name0'}).set(1, {id: 1, name: 'name1'}) };
		mutateOnTagLoaded(state, tags);
		expect(state).toStrictEqual(expectedState);
	});

	test('should check mutateOnTagCreated if tag with this id is present', () => {
		const tag = { id, name };
		const expectedState = { tags: new Map().set(id, {id, name: 'old.name'}) };

		let state = { tags: new Map().set(id, {id, name: 'old.name'}) };
		mutateOnTagCreated(state, tag);
		expect(state).toStrictEqual(expectedState);
	});

	test('should check mutateOnTagCreated if tag with this id is absent', () => {
		const tag = { id, name };
		const expectedState = { tags: new Map().set(id, tag) };

		let state = { tags: new Map() };
		mutateOnTagCreated(state, tag);
		expect(state).toStrictEqual(expectedState);
	});

	test('should check mutateOnTagUpdated if tag with this id is present', () => {
		const tag = { id, name };
		const expectedState = { tags: new Map().set(id, tag) };

		let state = {tags: new Map().set(id, {id, name: 'old.name'})};
		mutateOnTagUpdated(state, tag);
		expect(state).toStrictEqual(expectedState);
	});

	test('should check mutateOnTagUpdated if tag with this id is absent', () => {
		const tag = { id, name };
		const expectedState = { tags: new Map() };

		let state = {tags: new Map()};
		mutateOnTagUpdated(state, tag);
		expect(state).toStrictEqual(expectedState);
	});

	test('should check mutateOnTagRemoved', () => {
		const tag = {id, name};
		const expectedState = { tags: new Map() };

		let state = { tags: new Map().set(id, tag) };
		mutateOnTagRemoved(state, id);
		expect(state).toStrictEqual(expectedState);
	});

	test('should check mutateOnTagClearing', () => {
		const tag = {id, name};

		let state = { tags: new Map().set(id, tag) };
		mutateOnTagClearing(state);
		expect(state.tags.size).toBe(0);
	});

	test('should check mutateOnTagItemSelect', () => {
		const expected = 123;

		let state = {selectedId: undefined};
		mutateOnTagItemSelect(state, expected);
		expect(state.selectedId).toBe(expected);
	})
});
