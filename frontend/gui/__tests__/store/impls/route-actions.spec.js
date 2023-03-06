import { actOnRouteChanging } from "../../../src/store/imps/route-actions";
import { ROUTE } from "../../../src/sconst/route";
import testCommit from "../../../__utils/test-commit";

describe('route-actions.js', () => {

	test('should check actOnRouteChanging-actions', () => {
		const route = '/route';
		const expectedCommitResult = [{command: ROUTE.ON.CHANGING, data: route}];

		actOnRouteChanging({commit: testCommit.commit}, route);
		expect(testCommit.getResult()).toStrictEqual(expectedCommitResult);
	});
});
