import config from "../../../config";
import { SIGNAL } from "../../../src/sconst/signal";
import {
	actOnSomeModalHide,
	actOnSomeModalShow,
	actOnTagModalHide,
	actOnTagModalShow
} from "../../../src/store/imps/signal-actions";
import testCommit from "../../../__utils/test-commit";

describe('signal-actions.js', () => {

	const route = 'some.route';

	test('should check actOnSomeModalShow action', () => {
		const expectedCommitResult = [{command: SIGNAL.MODAL.SOME.ADD.SHOW, data: route}];

		actOnSomeModalShow({commit: testCommit.commit}, route);
		expect(testCommit.getResult()).toStrictEqual(expectedCommitResult);
	});

	test('should check actOnSomeModalHide action', () => {
		const expectedCommitResult = [{command: SIGNAL.MODAL.SOME.ADD.HIDE, data: route}];

		actOnSomeModalHide({commit: testCommit.commit}, route);
		expect(testCommit.getResult()).toStrictEqual(expectedCommitResult);
	});

	test('should check actOnTagModalShow', () => {
		const expected = [{command: SIGNAL.MODAL.SOME.ADD.SHOW, data: config.paths.tags}];

		actOnTagModalShow({commit: testCommit.commit});
		expect(testCommit.getResult()).toStrictEqual(expected);
	});

	test('should check actOnTagModalHide', () => {
		const expected = [{command: SIGNAL.MODAL.SOME.ADD.HIDE, data: config.paths.tags}];

		actOnTagModalHide({commit: testCommit.commit});
		expect(testCommit.getResult()).toStrictEqual(expected);
	});
});
