<template>
	<!-- //<  -->
	<!-- :style="{'grid-template-columns': gridTemplateColumns}" -->
	<div class="v-table-row">
		<v-table-cell
			v-for="(attribute, index) in attributes"
			:key="index"
			:datasource="datasource"
			:id="id",
			:attribute="attribute"
		/>
	</div>
</template>

<script>
	import vTableCell from './v-table-cell';

	export default {
		name: 'v-table-row',
		components: {
			vTableCell
		},
		props: {
			datasource: {
				type: String,
				required: true
			},
			id: {
				type: Number,
				required: true
			},
			attributes: {
				type: Array,
				required: true
			},
			even: {
				type: Boolean,
				default: false
			},
		},
		data() {
			return {}
		},
		computed: {
			//<
			// gridTemplateColumns() {
			// 	let delimiter = '';
			// 	let result = '';
			// 	for(const datum of this.data){
			// 		result += delimiter + (datum.width !== undefined ? datum.width : '1fr');
			// 		delimiter = ' ';
			// 	}

			// 	return result;
			// }
		},
		methods: {}
	}
</script>

<style>
	.v-table-row {
		border: solid black 1px;
		height: 30px;
		display: grid;
	}
</style>
