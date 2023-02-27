<template>
	<div class="v-table-header-cell">
		<p class="v-table-header-cell-text">{{ translate(code, args) }}</p>
	</div>
</template>

<script>
	import { mapGetters } from 'vuex';

	export default {
		name: 'v-table-header-cell',
		props: {
			code: {
				type: String,
				required: true
			},
			args: {
				type: Object,
				default: () => { return {}; }
			}
		},
		data() {
			return {}
		},
		computed: {
			...mapGetters(['translate'])
		},
		methods: {}
	}
</script>

<style lang="scss">
	.v-table-header-cell {
		border: solid black 1px;
		height: 36px;
		background: #4f688c4f;
	}

	.v-table-header-cell-text {
		margin: 0 auto;
		font-size: 24px;
		font-weight: 700;
		margin-top: 3px;
		font-family: $commonFontFamily;
	}
</style>
