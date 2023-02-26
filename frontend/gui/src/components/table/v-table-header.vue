<template>
	<div
		class="v-table-header"
		:style="{'grid-template-columns': gridTemplateColumns}"
	>
		<v-table-header-cell
			v-for="(datum, index) in data"
			:key="index"
			:code="datum.code"
		/>
	</div>
</template>

<script>
	import vTableHeaderCell from './v-table-header-cell';

	export default {
		name: 'v-table-header',
		components: {
			vTableHeaderCell
		},
		props: {
			data: {
				type: Array,
				required: true
			}
		},
		data() {
			return {}
		},
		computed: {
			gridTemplateColumns() {
				let delimiter = '';
				let result = '';
				for(const datum of this.data){
					result += delimiter + (datum.width !== undefined ? datum.width : '1fr');
					delimiter = ' ';
				}

				return result;
			}
		},
		methods: {}
	}
</script>

<style>
	.v-table-header {
		border: solid black 0px;
		height: 36px;
		display: grid;
	}
</style>
