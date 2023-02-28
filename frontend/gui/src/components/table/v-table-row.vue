<template>
	<div
		class="v-table-row"
		:style="{'grid-template-columns': gridTemplateColumns}"
	>
		<v-table-cell
			v-for="(attribute, index) in attributes"
			:key="index"
			:datasource="datasource"
			:id="id"
			:attribute="attribute"
			:even="even"
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
			}
		},
		data() {
			return {}
		},
		computed: {
			gridTemplateColumns() {
				let delimiter = '';
				let result = '';
				for (const attribute of this.attributes){
					result += delimiter + (attribute.width !== undefined ? attribute.width : '1fr');
					delimiter = ' ';
				}

				return result;
			}
		},
		methods: {}
	}
</script>

<style>
	.v-table-row {
		border: solid black 0px;
		height: 30px;
		display: grid;
	}
</style>
