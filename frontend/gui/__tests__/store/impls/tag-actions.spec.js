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
import testCommit from "../../../__utils/test-commit";
import testDispatch from "../../../__utils/test-dispatch";

describe('tag-actions.js', () => {

	const id = 123;
	const name = 'some.name';

	test('should check actOnTagLoadingRequest', () => {
		const expectedDispatchResult = [{
			command: CONNECTION.SEND,
			data: {
				destination: config.requests.tag.load,
				headers: {},
				body: {}
			}
		}];

		actOnTagLoadingRequest({dispatch: testDispatch.dispatch});
		expect(testDispatch.getResult()).toStrictEqual(expectedDispatchResult);
	});

	test('should check actOnTagCreationRequest', () => {
		const expectedDispatchResult = [{
			command: CONNECTION.SEND,
			data: {
				destination: config.requests.tag.create,
				headers: {},
				body: name
			}
		}];

		actOnTagCreationRequest({dispatch: testDispatch.dispatch}, name);
		expect(testDispatch.getResult()).toStrictEqual(expectedDispatchResult);
	});

	test('should check actOnTagLoadingResponse', () => {
		const tags = [{ id, name }];
		const expected = [{
			command: TAG.RESPONSE.LOAD,
			data: tags
		}];

		actOnTagLoadingResponse({commit: testCommit.commit}, tags);
		expect(testCommit.getResult()).toStrictEqual(expected);
	});

	test('should check actOnTagCreationResponse', () => {
		const tag = { id, name };
		const expected = [{
			command: TAG.RESPONSE.CREATE,
			data: tag
		}];

		actOnTagCreationResponse({commit: testCommit.commit}, tag);
		expect(testCommit.getResult()).toStrictEqual(expected);
	});

	test('should check actOnTagCleaning', () => {
		const expected = [{
			command: TAG.STORAGE.CLEAR,
		}];

		actOnTagCleaning({commit: testCommit.commit});
		expect(testCommit.getResult()).toStrictEqual(expected);
	});
});
