import { Connection } from "../../src/connection/connection.js";

class HeadersCheckingClient {
	connect(headers, openCallback, errorCallback, closeCallback){
		this.headers = headers;
	}
	disconnect(){}
	send (destination, headers, body){}
}

class OpenCallbakcCheckingClient {
	connect(headers, openCallback, errorCallback, closeCallback){
		openCallback();
	}
	disconnect(){}
	send (destination, headers, body){}
}

class ErrorCallbakcCheckingClient {
	connect(headers, openCallback, errorCallback, closeCallback){
		errorCallback();
	}
	disconnect(){}
	send (destination, headers, body){}
}

class CloseCallbakcCheckingClient {
	connect(headers, openCallback, errorCallback, closeCallback){
		closeCallback();
	}
	disconnect(){}
	send (destination, headers, body){}
}

class DisconnectionCheckingClient{
	connect(headers, openCallback, errorCallback, closeCallback){}
	disconnect(){
		this.disconnected = true;
	}
	send (destination, headers, body){}
}

class SendCheckingClient{
	connect(headers, openCallback, errorCallback, closeCallback){
		openCallback();
	}
	disconnect(){}
	send (destination, headers, body){
		this.destination = destination;
		this.headers = headers;
		this.body = body;
	}
}

describe('connection.js', () => {

	test('should check headers usage', () => {
		const expectedHeaders = {"arg": "value"};

		const client = new HeadersCheckingClient();
		const creator = () => {return client;};

		new Connection(creator, expectedHeaders).connect();
		expect(client.headers).toStrictEqual(expectedHeaders);
	});

	test('should check openCallback usage', () => {
		let externalCallbackState = false;
		const creator = () => {return new OpenCallbakcCheckingClient();};

		const connection = new Connection(creator, {});
		connection.openCallback = () => {externalCallbackState = true;};

		connection.connect();
		expect(connection.connected).toBe(true);
		expect(externalCallbackState).toBe(true);
	});

	test('should check errorCallback usage', () => {
		let externalCallbackState = false;
		const creator = () => {return new ErrorCallbakcCheckingClient();};

		const connection = new Connection(creator, {});
		connection.errorCallback = () => {externalCallbackState = true;};
		connection.connect();
		expect(connection.connected).toBe(false);
		expect(externalCallbackState).toBe(true);
	});

	test('should check closeCallback usage', () => {
		let externalCallbackState = false;
		const creator = () => {return new CloseCallbakcCheckingClient();};

		const connection = new Connection(creator, {});
		connection.closeCallback = () => {externalCallbackState = true;};
		connection.connect();
		expect(connection.connected).toBe(false);
		expect(externalCallbackState).toBe(true);
	});

	test('should check disconnection', () => {
		const client = new DisconnectionCheckingClient();
		const creator = () => {return client;};

		const connection = new Connection(creator, {});
		connection.disconnect();
		expect(client.disconnected).toBe(true);
		expect(connection.connected).toBe(false);
	});

	test('should check sending if disconnected', () => {
		const expectedDestination = 'dest/ina/tion';
		const expectedHeaders = {arg: 'value'}
		const expectedBody = 'body';

		const client = new SendCheckingClient();
		const creator = () => {return client};

		const connection = new Connection(creator, {});
		connection.send(expectedDestination, expectedHeaders, expectedBody);

		expect(client.destination).toBeUndefined();
		expect(client.headers).toBeUndefined();
		expect(client.body).toBeUndefined();
	});

	test('should check sending if connected', () => {
		const expectedDestination = 'dest/ina/tion';
		const expectedHeaders = {arg: 'value'}
		const expectedBody = 'body';

		const client = new SendCheckingClient();
		const creator = () => {return client};

		const connection = new Connection(creator, {});
		connection.connect();
		connection.send(expectedDestination, expectedHeaders, expectedBody);

		expect(client.destination).toBe(expectedDestination);
		expect(client.headers).toBe(expectedHeaders);
		expect(client.body).toBe(expectedBody);
	});
});
