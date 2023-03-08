<template>
	<div
		class="v-table-cell"
		:class="{ even: even }"
	>
		<p class="v-table-cell-text" v-if="isText">{{ asText }}</p>
		<p class="v-table-cell-text" v-if="isNumber">{{ asNumber }}</p>
		<img
			class="v-table-cell-lamp"
			v-if="isLamp"
			:src="require(`../../assets/icons/lamps/${asLamp}.svg`)"
			alt=""
		>
	</div>
</template>

<script>
	import { CELL, toText, toNumber, toLamp } from '../../sconst/cell';

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
				type: Object,
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
			isText() { return this.attribute.type === CELL.TYPE.TEXT; },
			isNumber() { return this.attribute.type === CELL.TYPE.NUMBER; },
			isLamp() { return this.attribute.type === CELL.TYPE.LAMP; },
			asText() {
				return toText(this.$store.getters[this.datasource](this.id, this.attribute.name));
			},
			asNumber() {
				return toNumber(this.$store.getters[this.datasource](this.id, this.attribute.name), this.attribute.decimalPlaces);
			},
			asLamp() {
				return toLamp(this.$store.getters[this.datasource](this.id, this.attribute.name));
			}
		},
		methods: {}
	}
</script>

<style lang="scss">
	.v-table-cell {
		border: solid black 0px;
		height: 30px;
	}

	.even {
		background: #bebed84f;
	}

	.v-table-cell-text {
		margin: 0 auto;
		font-size: 20px;
		margin-top: 3px;
		font-family: $commonFontFamily;
	}

	.v-table-cell-lamp {
		width: 26px;
		margin-top: 2px;
	}
</style>
