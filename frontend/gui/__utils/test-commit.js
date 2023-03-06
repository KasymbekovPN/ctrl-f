
let result = [];

const commit = (command, data) => {
	result.push(data !== undefined ? { command, data } : { command });
};

const getResult = () => {
	const r = result;
	result = [];
	return r;
};

export default {
	commit,
	getResult
};
