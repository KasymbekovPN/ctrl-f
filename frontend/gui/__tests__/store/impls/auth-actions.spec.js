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

describe('auth-actions.js', () => {

	let commitResult;
	const commit = (command, data) => {
		commitResult = data !== undefined ? { command, data } : { command };
	};
	let dispatchResult = [];
	const dispatch = (command, data) => {
		dispatchResult.push(data !== undefined ? { command, data } : { command });
	};

	class Router {
		push (path) {
			this.path = path;
		}
	}

	const reset = () => {
		dispatchResult = [];
		commitResult = undefined;
	};

	test('should check requestLogin-actions', () => {
		const user = {data: 'user'};
		const expectedCommitResult = {command: AUTH.LOGIN.REQUEST};
		const expectedDispatchResult = [{
			command: CONNECTION.SEND,
			data: {
				destination: config.requests.auth,
				headers: {},
				body: user
			}
		}];

		requestLogin({commit, dispatch}, user);
		expect(commitResult).toStrictEqual(expectedCommitResult);
		expect(dispatchResult).toStrictEqual(expectedDispatchResult);
		reset();
	});

	test('should check responseLogin-actions if response is fail', () => {
		const response = {success: false};
		const expectedCommitResult = {command: AUTH.LOGIN.ERROR};
		const expectedDispatchResult = [{
			command: NOTIFICATION.LEVEL.ERROR,
			data: {
				code: 'login-page.state.error'
			}
		}];
		const router = new Router();

		responseLogin({commit, dispatch, router}, response);
		expect(dispatchResult).toStrictEqual(expectedDispatchResult);
		expect(commitResult).toStrictEqual(expectedCommitResult);
		expect(router.path).toBe(config.paths.home);
		reset();
	});

	test('should check responseLogin-actions', () => {
		const response = {
			success: true,
			value: { token: 'some token', username: 'some username' }
		};
		const expectedCommitResult = {command: AUTH.LOGIN.SUCCESS, data: response.value};
		const expectedDispatchResult = [
			{
				command: USER.PROFILE.SET,
				data: response.value
			},
			{
				command: DOMAIN_MANAGER.AFTER.LOGIN
			}
		];
		const router = new Router();

		responseLogin({commit, dispatch, router}, response);
		expect(commitResult).toStrictEqual(expectedCommitResult);
		expect(dispatchResult).toStrictEqual(expectedDispatchResult);
		expect(router.path).toBe(config.paths.home);
		reset();
	});


	test('should check requestLogout-actions', () => {
		const expectedCommitResult = {command: AUTH.LOGOUT.REQUEST};
		const expectedDispatchResult = [{
			command: CONNECTION.SEND,
			data: {
				destination: config.requests.logout,
				headers: {},
				body: {}
			}
		}];

		requestLogout({commit, dispatch});
		expect(commitResult).toStrictEqual(expectedCommitResult);
		expect(dispatchResult).toStrictEqual(expectedDispatchResult);
		reset();
	});

	test('should check responseLogout-actions if response is fail', () => {
		const response = {success: false};
		const expectedCommitResult = {command: AUTH.LOGOUT.ERROR};
		const expectedDispatchResult = [
			{
				command: USER.PROFILE.RESET,
				data: response
			},
			{
				command: DOMAIN_MANAGER.AFTER.LOGOUT
			}
		];
		const router = new Router();

		responseLogout({commit, dispatch, router}, response);
		expect(commitResult).toStrictEqual(expectedCommitResult);
		expect(dispatchResult).toStrictEqual(expectedDispatchResult);
		expect(router.path).toBe(config.paths.login);
		reset();
	});

	test('should check responseLogout-actions', () => {
		const response = {success: true};
		const expectedCommitResult = {command: AUTH.LOGOUT.SUCCESS, data: response};
		const expectedDispatchResult = [
			{
				command: USER.PROFILE.RESET,
				data: response
			},
			{
				command: DOMAIN_MANAGER.AFTER.LOGOUT
			}
		];
		const router = new Router();

		responseLogout({commit, dispatch, router}, response);
		expect(commitResult).toStrictEqual(expectedCommitResult);
		expect(dispatchResult).toStrictEqual(expectedDispatchResult);
		expect(router.path).toBe(config.paths.login);
		reset();
	});

	test('should check actOnDisconnection', () => {
		const expectedCommitResult = { command: AUTH.ON.DISCONNECTED };

		const router = new Router();
		actOnDisconnected({commit, router});
		expect(commitResult).toStrictEqual(expectedCommitResult);
		expect(router.path).toBe(config.paths.login);
	});
});
