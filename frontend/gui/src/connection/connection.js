class Connection {

	constructor(clientCreator, headers){
		this._connected = false;
		this._subscription = new Map();
		this._client = clientCreator();
		this._headers = headers;
	}

	get connected(){
		return this._connected;
	}

	addSubscription(path, callback){
		this._subscription.set(path, callback);
		return this;
	}

	connect() {
		this._client.connect(
			this._headers,
			frame => {
				this._connected = true;
				this._subscription.forEach((value, key) => {
					this._client.subscribe(key, value);
				});
			},
			error => {
				this.disconnect();
			},
			closeEvent => {
				this.disconnect();
			}
		);
	}

	disconnect(){
		this._connected = false;
		this._client.disconnect();
	}

	send (destination, headers, body){
		if (this._connected){
			this._client.send(destination, headers, body);
		}
	}
}

export {
	Connection
};
