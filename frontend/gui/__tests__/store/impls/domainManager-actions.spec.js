import {
	actDomainManagerAfterLogin,
	actDomainManagerAfterLogout
} from "../../../src/store/imps/domainManager-action";
import { DOMAIN_MANAGER } from "../../../src/sconst/domainManager";
import testDispatch from "../../../__utils/test-dispatch";

describe('domainManager-actions.js', () => {

	test('should check actDomainManagerAfterLogin', () => {
		actDomainManagerAfterLogin({dispatch: testDispatch.dispatch});

		const expected = [];
		for (const request of DOMAIN_MANAGER.LOAD_REQUESTS){
			expected.push({command: request});
		}
		expect(testDispatch.getResult()).toStrictEqual(expected);
	});

	test('should check actDomainManagerAfterLogout', () => {
		actDomainManagerAfterLogout({dispatch: testDispatch.dispatch});

		const expected = [];
		for (const request of DOMAIN_MANAGER.REMOVE_REQUESTS){
			expected.push({command: request});
		}
		expect(testDispatch.getResult()).toStrictEqual(expected);
	});
});
