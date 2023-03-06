import {
	doOnConnection,
	doOnDisconnection,
	doOnSending,
	actOnConnectionCreation
} from "../../../src/store/imps/connection-actions";
import { CONNECTION } from "../../../src/sconst/connection";
import testCommit from "../../../__utils/test-commit";
import testDispatch from "../../../__utils/test-dispatch";

describe('connection-actions.js', () => {

	class TestConnection {}

	test('should check actOnConnectionCreation-actions', () => {
		const sessionId = 'sessionId';
		const connection = new TestConnection();
		const expectedCommitResult = [{
			command: CONNECTION.CREATE,
			data: {connection, sessionId}
		}];
		const expectedDispatchResult = [{command: CONNECTION.CONNECT}];

		actOnConnectionCreation(testCommit.commit, testDispatch.dispatch, connection, sessionId);
		expect(testCommit.getResult()).toStrictEqual(expectedCommitResult);
		expect(testDispatch.getResult()).toStrictEqual(expectedDispatchResult);
	});

	test('should check doOnConnection-actions', () => {
		const expectedCommitResult = [{command: CONNECTION.CONNECT}];

		doOnConnection({commit: testCommit.commit});
		expect(testCommit.getResult()).toStrictEqual(expectedCommitResult);
	});

	test('should check doOnDisconnection-actions', () => {
		const expectedCommitResult = [{command: CONNECTION.DISCONNECT}];

		doOnDisconnection({commit: testCommit.commit});
		expect(testCommit.getResult()).toStrictEqual(expectedCommitResult);
	});

	test('should check doSending-actions', () => {
		const destination = 'some destination';
		const headers = {};
		const body = {};
		const expectedCommitResult = [{
			command: CONNECTION.SEND,
			data: {destination, headers, body}
		}];

		doOnSending({commit: testCommit.commit}, {destination, headers, body});
		expect(testCommit.getResult()).toStrictEqual(expectedCommitResult);
	});
});
