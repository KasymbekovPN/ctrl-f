import each from "jest-each";
import {
	TEXT,
	toText,
	toNumber,
	LAMP,
	toLamp
} from "../../src/sconst/cell";

describe('cell.js', () => {
	each([
		[undefined, TEXT.UNDEFINED],
		[null, TEXT.NULL],
		[Symbol(), TEXT.SYMBOL],
		[{}, TEXT.OBJECT],
		[[], TEXT.OBJECT],
		[new Map(), TEXT.OBJECT],
		[12, '12'],
		[123.456, '123.456'],
		[456n, '456'],
		[true, TEXT.TRUE],
		[false, TEXT.FALSE],
		['text', 'text']
	]).it('toText: when input is %s then returned value must be equal "%s"', (input, expected) =>{
		const result = toText(input);
		expect(result).toBe(expected);
	});

	each([
		[undefined, TEXT.UNDEFINED, undefined],
		[null, TEXT.NULL, undefined],
		[Symbol(), TEXT.SYMBOL, undefined],
		[{}, TEXT.OBJECT, undefined],
		[[], TEXT.OBJECT, undefined],
		[new Map(), TEXT.OBJECT, undefined],
		[12, '12', undefined, undefined],
		[123.456, '123', -1],
		[123.456, '123', 0],
		[123.456, '123.5', 1],
		[123.456, '123.46', 2],
		[123.456, '123.456', 3],
		[123.456, '123.456', 5],
		[123.456, '123.46', '2'],
		[123.456, '123.456', '5'],
		[123.456, '123.456', 'x'],
		[123.456, '123.456', {}],
		[456n, '456', undefined],
		[true, TEXT.TRUE_AS_NUMBER, undefined],
		[false, TEXT.FALSE_AS_NUMBER, undefined],
		['text', TEXT.TEXT, undefined]
	]).it('toNumber: when input is %s then returned value must be equal "%s", decimal places: %s', (input, expected, decimalPlaces) => {
		const result = toNumber(input, decimalPlaces);
		expect(result).toBe(expected);
	});

	each([
		[undefined, LAMP.UNKNOWN],
		[null, LAMP.UNKNOWN],
		[Symbol(), LAMP.UNKNOWN],
		[{}, LAMP.UNKNOWN],
		[[], LAMP.UNKNOWN],
		[new Map(), LAMP.UNKNOWN],
		[12, LAMP.UNKNOWN],
		[456n, LAMP.UNKNOWN],
		[true, LAMP.TRUE],
		[false, LAMP.FALSE],
		['text', LAMP.UNKNOWN]
	]).it('toLamp: when input is %s then returned value must be equal "%s"', (input, expected) => {
		const result = toLamp(input);
		expect(result).toBe(expected);
	});
});
