import config from "../../../config";
import {
	actOnDisconnected,
	requestLogin,
	requestLogout,
	responseLogin,
	responseLogout
} from "../../../src/store/imps/auth-actions";
import { AUTH } from "../../../src/sconst/auth";
import { CONNECTION } from "../../../src/sconst/connection";
import { USER } from "../../../src/sconst/userProfile";
import { NOTIFICATION } from "../../../src/sconst/notification";
import { DOMAIN_MANAGER } from "../../../src/sconst/domainManager";
import TestRouter from '../../../__utils/test-router';
import testCommit from "../../../__utils/test-commit";
import testDispatch from "../../../__utils/test-dispatch";

describe('auth-actions.js', () => {

	test('should check requestLogin-actions', () => {
		const user = {data: 'user'};
		const expectedCommitResult = [{command: AUTH.LOGIN.REQUEST}];
		const expectedDispatchResult = [{
			command: CONNECTION.SEND,
			data: {
				destination: config.requests.auth,
				headers: {},
				body: user
			}
		}];

		requestLogin({commit: testCommit.commit, dispatch: testDispatch.dispatch}, user);
		expect(testCommit.getResult()).toStrictEqual(expectedCommitResult);
		expect(testDispatch.getResult()).toStrictEqual(expectedDispatchResult);
	});

	test('should check responseLogin-actions if response is fail', () => {
		const response = {success: false};
		const expectedCommitResult = [{command: AUTH.LOGIN.ERROR}];
		const expectedDispatchResult = [{
			command: NOTIFICATION.LEVEL.ERROR,
			data: {
				code: 'login-page.state.error'
			}
		}];
		const router = new TestRouter();

		responseLogin({commit: testCommit.commit, dispatch: testDispatch.dispatch, router}, response);
		expect(testDispatch.getResult()).toStrictEqual(expectedDispatchResult);
		expect(testCommit.getResult()).toStrictEqual(expectedCommitResult);
		expect(router.path).toBe(config.paths.home);
	});

	test('should check responseLogin-actions', () => {
		const response = {
			success: true,
			value: { token: 'some token', username: 'some username' }
		};
		const expectedCommitResult = [{command: AUTH.LOGIN.SUCCESS, data: response.value}];
		const expectedDispatchResult = [
			{
				command: USER.PROFILE.SET,
				data: response.value
			},
			{
				command: DOMAIN_MANAGER.AFTER.LOGIN
			}
		];
		const router = new TestRouter();

		responseLogin({commit: testCommit.commit, dispatch: testDispatch.dispatch, router}, response);
		expect(testCommit.getResult()).toStrictEqual(expectedCommitResult);
		expect(testDispatch.getResult()).toStrictEqual(expectedDispatchResult);
		expect(router.path).toBe(config.paths.home);
	});

	test('should check requestLogout-actions', () => {
		const expectedCommitResult = [{command: AUTH.LOGOUT.REQUEST}];
		const expectedDispatchResult = [{
			command: CONNECTION.SEND,
			data: {
				destination: config.requests.logout,
				headers: {},
				body: {}
			}
		}];

		requestLogout({commit: testCommit.commit, dispatch: testDispatch.dispatch});
		expect(testCommit.getResult()).toStrictEqual(expectedCommitResult);
		expect(testDispatch.getResult()).toStrictEqual(expectedDispatchResult);
	});

	test('should check responseLogout-actions if response is fail', () => {
		const response = {success: false};
		const expectedCommitResult = [{command: AUTH.LOGOUT.ERROR}];
		const expectedDispatchResult = [
			{
				command: USER.PROFILE.RESET,
				data: response
			},
			{
				command: DOMAIN_MANAGER.AFTER.LOGOUT
			}
		];
		const router = new TestRouter();

		responseLogout({commit: testCommit.commit, dispatch: testDispatch.dispatch, router}, response);
		expect(testCommit.getResult()).toStrictEqual(expectedCommitResult);
		expect(testDispatch.getResult()).toStrictEqual(expectedDispatchResult);
		expect(router.path).toBe(config.paths.login);
	});

	test('should check responseLogout-actions', () => {
		const response = {success: true};
		const expectedCommitResult = [{command: AUTH.LOGOUT.SUCCESS, data: response}];
		const expectedDispatchResult = [
			{
				command: USER.PROFILE.RESET,
				data: response
			},
			{
				command: DOMAIN_MANAGER.AFTER.LOGOUT
			}
		];
		const router = new TestRouter();

		responseLogout({commit: testCommit.commit, dispatch: testDispatch.dispatch, router}, response);
		expect(testCommit.getResult()).toStrictEqual(expectedCommitResult);
		expect(testDispatch.getResult()).toStrictEqual(expectedDispatchResult);
		expect(router.path).toBe(config.paths.login);
	});

	test('should check actOnDisconnection', () => {
		const expectedCommitResult = [{ command: AUTH.ON.DISCONNECTED }];

		const router = new TestRouter();
		actOnDisconnected({commit: testCommit.commit, router});
		expect(testCommit.getResult()).toStrictEqual(expectedCommitResult);
		expect(router.path).toBe(config.paths.login);
	});
});
