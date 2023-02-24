
export default class Notifications {

	constructor(delay, size){
		this._delay = delay;
		this._size = size > 0 ? size : 1;
		this._data = new Map();
		this._timerIds = new Map();
	}

	put(notificationId, notification) {
		if (!this._data.has(notificationId)){
			if (this._data.size >= this._size){
				const id = this._data.entries().next().value[0];
				this.remove(id);
			}

			const timerId = setTimeout(() => {
				this.remove(notificationId);
			}, this._delay);
			this._data.set(notificationId, notification);
			this._timerIds.set(notificationId, timerId);
		}
	}

	get(){
		let result = [];
		for (let [id, value] of this._data){
			result.unshift(Object.assign({id}, value));
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
