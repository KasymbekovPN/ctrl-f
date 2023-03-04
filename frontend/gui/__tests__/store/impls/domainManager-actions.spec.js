import {
	actDomainManagerAfterLogin,
	actDomainManagerAfterLogout
} from "../../../src/store/imps/domainManager-action";
import { DOMAIN_MANAGER } from "../../../src/sconst/domainManager";

describe('domainManager-actions.js', () => {
	let dispatchResult = [];
	const dispatch = (command) => { dispatchResult.push(command); };
	const reset = () => { dispatchResult = []; };

	test('should check actDomainManagerAfterLogin', () => {
		actDomainManagerAfterLogin({dispatch});

		expect(dispatchResult).toStrictEqual(DOMAIN_MANAGER.LOAD_REQUESTS);
		reset();
	});

	test('should check actDomainManagerAfterLogout', () => {
		actDomainManagerAfterLogout({dispatch});

		expect(dispatchResult).toStrictEqual(DOMAIN_MANAGER.REMOVE_REQUESTS);
		reset();
	});
});
