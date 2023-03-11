<template>
	<transition name="modal-fade">
		<div
			class="modal-overlay"
			@click="onClose"
		>
			<div
				class="modal"
				@click.stop
			>
				<p class="modal-title">{{ translate('dialog.tag.title') }}</p>
				<hr>
				<input
					class="modal-name-input"
					v-model="name"
					:placeholder="[[ translate('dialog.tag.input.placeholder') ]]"
				/>
				<div class="modal-buttons">
					<button
						class="modal-btn-save"
						@click="onSaveButtonClick"
					>
						{{ translate('dialog.btn.save.name') }}
					</button>
					<button
						class="modal-btn-close"
						@click="onClose"
					>
						{{ translate('dialog.btn.close.name') }}
					</button>
					<button
						v-if="deleteButtonVisible"
						class="modal-btn-delete"
						@click="onDeleteButtonClick"
					>{{ translate('dialog.btn.delete.name') }}</button>
				</div>
			</div>
		</div>
	</transition>
</template>

<script>
	import { mapGetters, mapActions } from 'vuex';
	import { TAG } from '../sconst/tag';

	export default {
		name: 'v-header',
		components: {},
		data() {
			return {
				id : undefined,
				name: '',
				deleteButtonVisible: false
			}
		},
		computed: {
			...mapGetters([
				'translate',
				'tagAttributeDatasource',
				'isTagModalVisible',
				'tagSelectedId'
			])
		},
		methods: {
			...mapActions({
				sendNewTag: TAG.REQUEST.CREATE,
				sendUpdatedTag: TAG.REQUEST.UPDATE,
				sendRemoveTag: TAG.REQUEST.REMOVE
			}),
			onSaveButtonClick: function() {
				if (this.id !== undefined){
					this.sendUpdatedTag({id: this.id, name: this.name});
				} else {
					this.sendNewTag({name: this.name});
				}
				this.onClose();
			},
			onClose: function(){
				this.$emit('close-modal');
			},
			onDeleteButtonClick: function() {
				if (this.id !== undefined){
					this.sendRemoveTag(this.id);
				}
				this.onClose();
			}
		},
		watch: {
			isTagModalVisible(newValue) {
				if (newValue){
					this.id = this.tagSelectedId();
					this.name = this.id !== undefined ? this.tagAttributeDatasource(this.id, 'name') : '';
					this.deleteButtonVisible = this.id !== undefined;
				} else {
					this.id = undefined;
					this.name = '';
					this.deleteButtonVisible = false;
				}
			}
		}
	}
</script>

<style lang="scss">

	.modal-fade-enter,
	.modal-fade-leave-to {
		opacity: 0;
	}
	.modal-fade-enter-active,
	.modal-fade-leave-active {
		transition: opacity 0.5s ease;
	}

	.modal-overlay {
		position: fixed;
		top: 0;
		bottom: 0;
		left: 0;
		right: 0;
		display: flex;
		justify-content: center;
		background-color: #000000da;
	}

	.modal {
		text-align: center;
		background-color: white;
		height: 200px;
		width: 400px;
		margin-top: 10%;
		padding: 15px 0;
		border-radius: 20px;
	}

	.modal-title {
		font-size: 24px;
		font-family: $commonFontFamily;
	}

	.modal-name-input {
		height: 24px;
		width: 300px;
	}

	.modal-btn-save {
		width: 80px;
		height: $buttonHeight;
		font-size: $buttonFontSize;
		background: $buttonBackground;
		border-width: $buttonBorderWisth;
		cursor: $buttonCursor;
		margin-top: 15px;
	}

	.modal-btn-close {
		width: 80px;
		height: $buttonHeight;
		font-size: $buttonFontSize;
		background: $buttonBackground;
		border-width: $buttonBorderWisth;
		cursor: $buttonCursor;
		margin-top: 15px;
		margin-left: 5px;
	}

	.modal-btn-delete {
		width: 80px;
		height: $buttonHeight;
		font-size: $buttonFontSize;
		background: $buttonBackground;
		border-width: $buttonBorderWisth;
		cursor: $buttonCursor;
		margin-top: 15px;
		margin-left: 5px;
	}
</style>
