const MAX_DECIMAL_PLACE_VALUE = 10;

const CELL = {
	TYPE: {
		TEXT: 'CELL_TYPE_TEXT',
		NUMBER: 'CELL_TYPE_NUMBER',
		LAMP: 'CELL_TYPE_LAMP'
	}
};

const LAMP = {
	TRUE: 'true-lamp',
	FALSE: 'false-lamp',
	UNKNOWN: 'unknown-lamp'
}

const TEXT = {
	UNDEFINED: '<undefined>',
	NULL: '<null>',
	SYMBOL: '<symbol>',
	OBJECT: '<object>',
	TRUE: 'true',
	FALSE: 'false',
	TEXT: '<text>',
	TRUE_AS_NUMBER: '<1>',
	FALSE_AS_NUMBER: '<0>'
}

const toText = input => {
	if (input === undefined){ return TEXT.UNDEFINED; }
	if (input === null){ return TEXT.NULL; }
	if (typeof input === 'symbol') { return TEXT.SYMBOL; }
	if (typeof input === 'object') { return TEXT.OBJECT; }
	if (typeof input === 'bigint') { return '' + input; }
	return '' + input;
};

const toNumber = (input, decimalPlaces) => {
	if (input === undefined){ return TEXT.UNDEFINED; }
	if (input === null){ return TEXT.NULL; }
	if (typeof input === 'symbol') { return TEXT.SYMBOL; }
	if (typeof input === 'object') { return TEXT.OBJECT; }
	if (typeof input === 'bigint') { return '' + input; }
	if (typeof input === 'string' || input instanceof String) { return TEXT.TEXT; }
	if (typeof input == 'boolean') {return input ? TEXT.TRUE_AS_NUMBER: TEXT.FALSE_AS_NUMBER; }

	if (isNaN(decimalPlaces)){
		return '' + input;
	}

	let places = decimalPlaces;
	if (places < 0) { places = 0; }
	if (places > MAX_DECIMAL_PLACE_VALUE){ places = MAX_DECIMAL_PLACE_VALUE; }

	const b = Math.pow(10, places);
	return '' +  Math.round(input * b) / b;
};

const toLamp = input => {
	if (typeof input === 'boolean'){
		return input ? LAMP.TRUE : LAMP.FALSE;
	} else {
		return LAMP.UNKNOWN;
	}
};

export {
	CELL,
	LAMP,
	TEXT,
	toText,
	toNumber,
	toLamp
};
