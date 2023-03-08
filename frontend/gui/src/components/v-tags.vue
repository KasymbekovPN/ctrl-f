<template>
	<div class="v-tags">
		<v-table
			indexesDatasource="tagIndexes"
			attributeDatasource="tagAttributeDatasource"
			:attributes="attributes"
			:selectItemAction="selectItemAction"
		/>
		<v-tags-dialog
			v-show="isTagModalVisible"
			@close-modal="closeModal"
		/>
	</div>
</template>

<script>
	import config from '../../config';
	import { mapGetters } from 'vuex';
	import { SIGNAL } from '../sconst/signal';
	import vTagsDialog from './v-tags-dialog';
	import vTable from './table/v-table';
	import { CELL } from '../sconst/cell';
	import { TAG } from '../sconst/tag';

	export default {
		name: 'v-tags',
		components: {
			vTagsDialog,
			vTable
		},
		props: {},
		data() {
			return {
				attributes: [
					{
						name: 'id',
						type: CELL.TYPE.TEXT,
						code: 'table.header.tag.id',
						width: '100px'
					},
					{
						name: 'name',
						type: CELL.TYPE.TEXT,
						code: 'table.header.tag.name'
					}
				],
				selectItemAction: TAG.SELECT.ITEM
			}
		},
		computed: {
			...mapGetters([
				'isTagModalVisible'
			])
		},
		methods: {
			closeModal: function(){
				this.$store.dispatch(SIGNAL.MODAL.SOME.ADD.HIDE, config.paths.tags);
			}
		}
	}
</script>
