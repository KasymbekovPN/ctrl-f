<template>
	<div :class="getNotificationItemClass">
		<div class="v-notification-item-top">
			<button
				class="v-notification-item-close-btn"
				@click="onClose"
			>
				<img
					class="v-notification-item-close-img"
					:src="require(`../../assets/icons/close-cross.svg`)"
					alt=""
				>
			</button>
		</div>
		<div class="v-notification-item-bottom">
			<img
				class="v-notification-item-level-img"
				:src="require(`../../assets/icons/notification.svg`)"
				alt=""
			>
			<p class="v-notification-item-content">{{ message }}</p>
		</div>
	</div>
</template>

<script>
	import { mapGetters } from 'vuex';
	import { getAlias, NOTIFICATION } from '../../sconst/notification';

	export default {
		name: 'v-notification-item',
		components: {},
		props: {
			datum: Object
		},
		data() {
			return {}
		},
		computed: {
			...mapGetters([
				'translate'
			]),
			message: function(){
				return this.translate(this.datum.code, this.datum.args);
			},
			getNotificationItemClass: function() {
				return `v-notification-item__${getAlias(this.datum.level)}`
			}
		},
		methods: {
			onClose: function() {
				this.$store.dispatch(NOTIFICATION.CLEAR.ID, this.datum.id);
			}
		}
	}
</script>

<style lang="scss">
	.v-notification-item {
		width: 420px;
		height: 75px;
		border: solid $notificationItemBorderColor 1px;
		border-radius: 4px;
		margin: 0px;
		&__success {
			background: #2bab2bdb;
		}
		&__info {
			background: #006180ad;
		}
		&__warning {
			background: #e4b404fc;
		}
		&__error {
			background: #e73232d9;
		}
	}

	.v-notification-item-top {
		display: flex;
		justify-content: flex-end;
		height: 16px;
		margin-right: 0px;
	}

	.v-notification-item-close-btn {
		width: 20px;
		height: 20px;
		border-width: 0px;
		margin-left: 5px;
		padding: 0px;
		background: #00000000;
		cursor: $buttonCursor;
	}

	.v-notification-item-close-img {
		width: 20px;
		height: 20px;
		margin: 0px;
	}

	.v-notification-item-bottom {
		display: flex;
	}

	.v-notification-item-level-img {
		height: 40px;
		width: 40px;
		margin-left: 10px;
	}

	.v-notification-item-content {
		margin-left: 10px;
		margin-top: 0px;
		text-align: left;
	}
</style>
