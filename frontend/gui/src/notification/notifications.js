
export default class Notifications {

	constructor(delay, size){
		this._delay = delay;
		this._size = size;
		this._data = new Map();
		this._timerIds = new Map();
	}

	put(notificationId, notification) {
		if (!this._data.has(notificationId)){
			const timerId = setTimeout(() => {
				this.remove(notificationId);
			}, this._delay);
			this._data.set(notificationId, notification);
			this._timerIds.set(notificationId, timerId);
		}
		//<
		console.log('-------');
		console.log('put');
		console.log(notificationId);
		console.log(notification);
		//<
	}

	get(){
		let result = [];
		for (let [id, valye] of this._data){
			result.push(Object.assign({id}, valye));
		}

		return result;
	}

	remove(notificationId) {
		if (this._timerIds.has(notificationId)){
			clearTimeout(this._timerIds.get(notificationId));
		}
		this._data.delete(notificationId);
		this._timerIds.delete(notificationId);
	}

	isEmpty() {
		return this._data.size === 0;
	}
}
