<template>
	<div class='v-notification'>
		<transition-group
			name="v-transition-animate"
		>
			<div
				class="v-notification__content"
				v-for="message in messages"
				:key="message.id"
			>
				<v-notification-item
					class="v-notification-item"
					:key="message.id"
					:datum="message"
				/>
			</div>
		</transition-group>
	</div>
</template>

<script>
	import vNotificationItem from './v-notification-item';

	export default {
		name: "v-notification",
		components: {
			vNotificationItem
		},
		props: {
			messages: {
				type: Array,
				default: () => {
					return []
				}
			}
		},
		data() {
			return {}
		}
	}
</script>

<style lang="scss">
	.v-notification {
		width: 420px;
		position: fixed;
		top: 65px;
		right: 0px;
		z-index: 10;

		&__content {
			padding: 16px;
			border-radius: 4px;
			color: #ffffff;
			display: flex;
			justify-content: space-between;
			align-items: center;
			height: 50px;
		}
	}
	.v-transition-animate {
		&-enter {
			transform: translateX(120px);
			opacity: 0;
		}
		&-enter-active {
			transition: all .6s ease;
		}
		&-enter-to {
			opacity: 1;
		}
		&-leave {
			height: 50px;
			opacity: 1;
		}
		&-leave-active {
			transition: transform .6s ease, opacity .6s, height .6s .2s;
		}
		&-leave-to {
			height: 0;
			transform: translateX(120px);
			opacity: 0;
		}
		&-move {
			transition: transform .6s ease;
		}
	}
</style>
