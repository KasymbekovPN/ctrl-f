import { I18N } from "../../../src/sconst/i18n";
import {
	setI18nLocale,
	setI18nTemplates
} from "../../../src/store/imps/i18n-actions";
import testCommit from "../../../__utils/test-commit";

describe('i18n-actions.js', () => {

	test('should check setI18nLocale-action', () => {
		const locale = 'en';
		const expectedResult = [{
			command: I18N.SET.LOCALE,
			data: locale
		}];

		setI18nLocale({commit: testCommit.commit}, locale);
		expect(testCommit.getResult()).toStrictEqual(expectedResult);
	});

	test('should check setI18nTemplates-action', () => {
		const data = {value: 'value'};
		const expectedResult = [{
			command: I18N.SET.TEMPLATES,
			data
		}];

		setI18nTemplates({commit: testCommit.commit}, data);
		expect(testCommit.getResult()).toStrictEqual(expectedResult);
	});
});
