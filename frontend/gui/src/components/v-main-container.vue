<template>
	<div class="v-main-container">
		<button @click="clickTest">value</button>
		<!-- <v-notification
			:messages="messages"
		/> -->
		<!-- //< -->
		<v-notification
			:messages="getNotifications"
		/>
		<div v-if="isConnected" class="v-main-container__connected">
			<v-header v-if="isAuthenticated"/>
			<div class="v-main-container__connected__main">
				<v-menu
					v-if="isAuthenticated"
					:items="items"
				/>
				<div class="v-main-container__connected__main__router_view">
					<router-view />
				</div>
			</div>
		</div>
		<div v-if="!isConnected" class="v-main-container__disconnected">
			<v-disconnection-page></v-disconnection-page>
		</div>
	</div>
</template>

<script>
	import config from "../../config";
	import { mapGetters } from 'vuex';
	import vDisconnectionPage from './v-disconnection-page';
	import vHeader from './v-header';
	import vMenu from './v-menu';
	import { ROUTE } from '../sconst/route';
	import vNotification from './notification/v-notification';
	import { NOTIFICATION } from '../sconst/notification';

	export default {
		name: 'v-main-container',
		components: {
			vDisconnectionPage,
			vHeader,
			vMenu,
			vNotification
		},
		props: {},
		data() {
			return {
				items: config.menu.items,
				//<
				messages: []
			}
		},
		computed: {
			...mapGetters([
				'isConnected',
				'isAuthenticated',
				'getNotifications'
			])
		},
		methods: {
			clickTest: function() {
				const id = Date.now().toLocaleString();
				const code = `${id} some code`;
				const args = {value: '123'};
				this.$store.dispatch(NOTIFICATION.INFO, {code, args});
				//<
				// this.messages.unshift({
				// 	id: id,
				// 	level: 'NOTIFICATION_INFO',
				// 	code: `${id} some code`,
				// 	args: {value: '123'}
				// });
			}
		},
		watch: {
			$route: function(to, /*from*/){
				this.$store.dispatch(ROUTE.ON.CHANGING, to.path);
			}
		}
	}
</script>

<style lang="scss">
	.v-main-container {
		text-align: center;

		&__connected {
			background: $connectionBackground;

			&__main {
				display: flex;

				&__router_view {
					width: 100%;
				}
			}
		}

		&__disconnected {
			background: $disconnectionBackground;
		}
	}
</style>
