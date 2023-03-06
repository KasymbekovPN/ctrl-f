import { resetUserProfile, setUserProfile } from "../../../src/store/imps/userProfile-actions";
import { USER } from "../../../src/sconst/userProfile";
import testCommit from "../../../__utils/test-commit";

describe('userProfile-actions.js', () => {

	test('should check setUserProfile-actions', () => {
		const data = {value: 'value'};
		const expectedResult = [{command: USER.PROFILE.SET, data}];

		setUserProfile({commit: testCommit.commit}, data);
		expect(testCommit.getResult()).toStrictEqual(expectedResult);
	});

	test('should check resetUserProfile-actions', () => {
		const expectedResult = [{command: USER.PROFILE.RESET}];

		resetUserProfile({commit: testCommit.getResult()});
		expect(testCommit.getResult()).toStrictEqual(expectedResult);
	});
});
