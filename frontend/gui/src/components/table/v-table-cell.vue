<template>
	<div class="v-table-cell">
		<!-- <p>{{ datasource }}</p>
		<p>{{ id }}</p>
		<p>{{ attribute }}</p>
		<p>{{ heigth }}</p>
		<p>{{ width }}</p>
		<p>{{ odd }}</p>
		<p>{{ type }}</p> -->

		<p>{{ textContext }}</p>
	</div>
</template>

<script>
	import { CELL } from '../../sconst/cell';

	export default {
		name: 'v-table-cell',
		props: {
			datasource: {
				type: String,
				required: true
			},
			id: {
				type: Number,
				required: true
			},
			attribute: {
				type: String,
				required: true
			},
			type: {
				type: String,
				default: CELL.TYPE.TEXT
			},
			heigth: {
				type: String,
				required: true
			},
			width: {
				type: String,
				required: true
			},
			odd: {
				type: Boolean,
				required: true
			},
		},
		data() {
			return {}
		},
		computed: {
			textContext: function() {
				//<
				// console.log('11111111111111111')
				// console.log(this.datasource);

				// console.log(this.ds);
				// console.log(this.ds(this.id, this.attribute));

				// console.log(this.$store);
				// console.log(this.$store.getters[this.datasource](this.id, this.attribute));

				//<
				return this.$store.getters[this.datasource](this.id, this.attribute);
			}
		},
		methods: {}
	}
</script>

<style lang="scss">
	.v-table-cell {
		border: solid black 1px;
	}
</style>
