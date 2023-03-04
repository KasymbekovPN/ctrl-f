import config from "../../../config";
import { CONNECTION } from "../../../src/sconst/connection";
import { TAG } from "../../../src/sconst/tag";
import {
	actOnTagLoadingRequest,
	actOnTagCreationRequest,
	actOnTagLoadingResponse,
	actOnTagCreationResponse,
	actOnTagCleaning
} from "../../../src/store/imps/tag-actions";

describe('tag-actions.js', () => {

	const id = 123;
	const name = 'some.name';

	let commitResult;
	const commit = (command, data) => { commitResult = data !== undefined ? { command, data } : { command }; };

	let dispatchResult;
	const dispatch = (command, data) => { dispatchResult = data !== undefined ? { command, data } : { command }; };

	afterEach(() => {
		commitResult = dispatchResult = undefined;
	});

	test('should check actOnTagLoadingRequest', () => {
		const expectedDispatchResult = {
			command: CONNECTION.SEND,
			data: {
				destination: config.requests.tag.load,
				headers: {},
				body: {}
			}
		};

		actOnTagLoadingRequest({dispatch});
		expect(dispatchResult).toStrictEqual(expectedDispatchResult);
	});

	test('should check actOnTagCreationRequest', () => {
		const expectedDispatchResult = {
			command: CONNECTION.SEND,
			data: {
				destination: config.requests.tag.create,
				headers: {},
				body: {name}
			}
		};

		actOnTagCreationRequest({dispatch}, name);
		expect(dispatchResult).toStrictEqual(expectedDispatchResult);
	});

	test('should check actOnTagLoadingResponse', () => {
		const tags = [{ id, name }];
		const expected = {
			command: TAG.RESPONSE.LOAD,
			data: tags
		};

		actOnTagLoadingResponse({commit}, tags);
		expect(commitResult).toStrictEqual(expected);
	});

	test('should check actOnTagCreationResponse', () => {
		const tag = { id, name };
		const expected = {
			command: TAG.RESPONSE.CREATE,
			data: tag
		};

		actOnTagCreationResponse({commit}, tag);
		expect(commitResult).toStrictEqual(expected);
	});

	test('should check actOnTagCleaning', () => {
		const expected = {
			command: TAG.STORAGE.CLEAR,
		};

		actOnTagCleaning({commit});
		expect(commitResult).toStrictEqual(expected);
	});
});
