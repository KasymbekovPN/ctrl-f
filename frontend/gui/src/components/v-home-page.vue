<template>
	<div class="v-home-page">
		<p>HOME</p>
		<!-- //< temp -->
		<!-- <v-notification-item :data="data" /> -->
	</div>
</template>

<script>
	//< tmp
	// import vNotificationItem from './notification/v-notification-item';
	// import { NOTIFICATION } from '../sconst/notification';
	// import vNotification from './notification/v-notification';
	//<

	export default {
		name: 'v-home-page',
		components: {
			//< tmp
			// vNotificationItem
			//<
			// vNotification
		},
		props: {},
		data() {
			return {
				//< tmp
				// data: {
				// 	id: 'some.id',
				// 	seed: {
				// 		code: '{arg0} some.code {arg1}',
				// 		args: {
				// 			arg0: 'value0',
				// 			arg1: 'value1'
				// 		}
				// 	},
				// 	level: NOTIFICATION.WARNING
				// }
				//<
			}
		},
		computed: {},
		methods: {}
	}
</script>

<style lang="scss">
	.v-home-page {
		display: flex;
		flex-direction: column;
		align-items: center;
		font-family: $commonFontFamily;
	}
</style>
