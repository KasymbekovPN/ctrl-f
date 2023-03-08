<template>
	<div class="v-table">
		<v-table-header
			:data="headerAttributes"
		/>
		<v-table-row
			v-for="index in indexes"
			:key="index"
			:datasource="attributeDatasource"
			:id="index"
			:attributes="tableAttributes"
			:even="index % 2 != 0"
			:selectItemAction="selectItemAction"
		/>
	</div>
</template>

<script>
	import vTableHeader from './v-table-header';
	import vTableRow from './v-table-row';

	export default {
		name: 'v-table',
		components: {
			vTableHeader,
			vTableRow
		},
		props: {
			indexesDatasource: {
				type: String,
				required: true,
			},
			attributeDatasource: {
				type: String,
				required: true
			},
			attributes:{
				type: Array,
				required: true
			},
			selectItemAction: {
				type: String,
				required:  true
			}
		},
		data() {
			return {}
		},
		computed: {
			indexes() {
				return this.$store.getters[this.indexesDatasource];
			},
			headerAttributes() {
				let result = [];
				for (const attribute of this.attributes){
					const datum = {code: attribute.code};
					if (attribute.width !== undefined){
						datum.width = attribute.width;
					}
					result.push(datum);
				}
				return result;
			},
			tableAttributes() {
				let result = [];
				for (const attribute of this.attributes){
					const datum = {name: attribute.name, type: attribute.type};
					if (attribute.width !== undefined){
						datum.width = attribute.width;
					}
					if (attribute.decimalPlaces !== undefined){
						datum.decimalPlaces = attribute.decimalPlaces;
					}
					result.push(datum);
				}
				return result;
			}
		},
		methods: {}
	}
</script>

<style lang="scss">
	.v-table {
		height: auto;
	}
</style>
