<!-- //< del -->

<template>
	<div class="v-notification">
		<div class="v-notification-top">
			<button
				class="v-notification-close-btn"
				@click="onClose"
			>
				<img
					class="v-notification-close-img"
					:src="require(`../../assets/icons/close-cross.svg`)"
					alt=""
				>
			</button>
		</div>
		<div class="v-notification-main">
			<v-notification-item
				class="v-notification-item"
				v-for="datum in data"
				:key="datum.id"
				:datum="datum"
			/>
		</div>
	</div>
</template>

<script>
	import vNotificationItem from './v-notification-item';
	import { NOTIFICATION } from '../../sconst/notification';

	export default {
		name: 'v-notification',
		components: {
			vNotificationItem
		},
		props: {},
		data() {
			return {
				//< tmp
				data: [
					{
						id: '0',
						level: NOTIFICATION.INFO,
						seed: {code: '{arg0} some.code {arg1}', args: { arg0: 'value0', arg1: 'value1'}}
					},
					{
						id: '1',
						level: NOTIFICATION.INFO,
						seed: {code: '{arg0} some.code {arg1}',args: {arg0: 'value10',arg1: 'value11'}}
					},
					{
						id: '2',
						level: NOTIFICATION.INFO,
						seed: {code: '{arg0} some.code {arg1}',args: {arg0: 'value10',arg1: 'value11'}}
					},
					{
						id: '3',
						level: NOTIFICATION.INFO,
						seed: {code: '{arg0} some.code {arg1}',args: {arg0: 'value10',arg1: 'value11'}}
					},
					{
						id: '4',
						level: NOTIFICATION.INFO,
						seed: {code: '{arg0} some.code {arg1}',args: {arg0: 'value10',arg1: 'value11'}}
					}
				]
			}
		},
		computed: {},
		methods: {
			onClose: function(){
				//<
				console.log(' --- v-notification onClose');
			}
		}
	}
</script>

<style lang="scss">
	.v-notification {
		height: 580px;
		width: 420px;
		border: solid $notificationItemBorderColor 1px;
		border-radius: 20px;
		box-shadow: 10px 5px 5px $notificationItemShadowColor;
	}

	.v-notification-top {
		display: flex;
		justify-content: flex-end;
		height: 30px;
		margin-right: 5px;
	}

	.v-notification-close-btn {
		width: 20px;
		height: 20px;
		background: #ffffff;
		border-width: 0px;
		cursor: $buttonCursor;
		margin-top: 5px;
		margin-left: 5px;
		padding: 0px;
	}

	.v-notification-close-img {
		width: 20px;
		height: 20px;
		margin: 0px;
	}

	.v-notification-item {
		margin-top: 5px;
		margin-left: 5px;
	}
</style>
