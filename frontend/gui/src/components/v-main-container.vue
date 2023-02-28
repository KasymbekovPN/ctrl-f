<template>
	<div class="v-main-container">
		<!-- //< del region begin -->
		<button @click="testOnClick">click me</button>

		<!-- <v-table-row
			:datasource="datasource"
			:id="id"
			:attributes="attributes"
			:even="even"
		/> -->

		<!-- <v-table-header
			:data="headerData"
		/> -->

		<!-- <v-table-header-cell
			:code="code"
		/>

		<v-table-cell
			:datasource="datasource"
			:id="id"
			:attribute="attribute"
			:even="even"
			:type="type"
			:decimalPlaces="decimalPlaces"
		/> -->
		<!-- //< del region end -->

		<v-notification
			v-if="isNotificationVisible"
			:messages="getNotifications"
		/>
		<div v-if="isConnected" class="v-main-container__connected">
			<v-header v-if="isAuthenticated"/>
			<div class="v-main-container__connected__main">
				<v-menu
					v-if="isAuthenticated"
					:items="items"
				/>
				<div class="v-main-container__connected__main__router_view">
					<router-view />
				</div>
			</div>
		</div>
		<div v-if="!isConnected" class="v-main-container__disconnected">
			<v-disconnection-page></v-disconnection-page>
		</div>
	</div>
</template>

<script>
	import config from "../../config";
	import { mapGetters } from 'vuex';
	import vDisconnectionPage from './v-disconnection-page';
	import vHeader from './v-header';
	import vMenu from './v-menu';
	import { ROUTE } from '../sconst/route';
	import vNotification from './notification/v-notification';

	//<
	// import vTableCell from './table/v-table-cell';
	// import vTableHeaderCell from './table/v-table-header-cell';
	// import vTableHeader from './table/v-table-header';
	// import vTableRow from './table/v-table-row';
	import { CELL } from '../sconst/cell';
	//<

	export default {
		name: 'v-main-container',
		components: {
			vDisconnectionPage,
			vHeader,
			vMenu,
			vNotification,
			//<
			// vTableCell,
			// vTableHeaderCell,
			// vTableHeader
			// vTableRow
			//<
		},
		props: {},
		data() {
			return {
				items: config.menu.items,
				//< temp
				datasource: "getDomainAttribute",
				id: 0,

				attributes: [
					{
						name: 'id',
						type: CELL.TYPE.TEXT,
						width: '100px'
					},
					{
						name: 'name',
						type: CELL.TYPE.TEXT,
						width: '100px'
					},
					{
						name: 'price',
						type: CELL.TYPE.NUMBER,
						decimalPlaces: 2,
						width: '100px'
					},
					{
						name: 'status',
						type: CELL.TYPE.LAMP
					}
				],

				attribute: "name",
				width: "1000px",
				even: false,
				type: CELL.TYPE.TEXT,
				decimalPlaces: 2,
				code: 'table.header.tag.id',
				// code: 'table.header.tag.name',
				headerData: [
					{
						code: 'table.header.tag.id',
						width: '100px'
					},
					{
						code: 'table.header.tag.name'
					}
				]
			}
		},
		computed: {
			...mapGetters([
				'isConnected',
				'isAuthenticated',
				'getNotifications',
				'isNotificationVisible'
			])
		},
		methods: {
			//< temp
			testOnClick: function(){
				this.$store.dispatch('testCellChange');
			}
		},
		watch: {
			$route: function(to, /*from*/){
				this.$store.dispatch(ROUTE.ON.CHANGING, to.path);
			}
		}
	}
</script>

<style lang="scss">
	.v-main-container {
		text-align: center;

		&__connected {
			background: $connectionBackground;

			&__main {
				display: flex;

				&__router_view {
					width: 100%;
				}
			}
		}

		&__disconnected {
			background: $disconnectionBackground;
		}
	}
</style>
