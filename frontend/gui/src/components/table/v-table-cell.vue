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
				type: String,
				required: true
			},
			type: {
				type: String,
				default: CELL.TYPE.TEXT
			},
			even: {
				type: Boolean,
				default: false
			},
			decimalPlaces: {
				type: Number,
				default: 2
			}
		},
		data() {
			return {}
		},
		computed: {
			isText() { return this.type === CELL.TYPE.TEXT; },
			isNumber() { return this.type === CELL.TYPE.NUMBER; },
			isLamp() { return this.type === CELL.TYPE.LAMP; },
			asText() {
				return toText(this.$store.getters[this.datasource](this.id, this.attribute));
			},
			asNumber() {
				return toNumber(this.$store.getters[this.datasource](this.id, this.attribute), this.decimalPlaces);
			},
			asLamp() {
				return toLamp(this.$store.getters[this.datasource](this.id, this.attribute));
			}
		},
		methods: {}
	}
</script>

<style lang="scss">
	.v-table-cell {
		border: solid black 1px;
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
