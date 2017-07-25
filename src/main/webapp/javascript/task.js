var appcontext = window.location.href.substring(window.location.origin.length);

window.todo.TaskModel = Backbone.Model.extend({
	urlRoot : appcontext + "webresources/tasks",
	idAttribute : "id",
	defaults : {
		status : "INCOMPLETE"
	}
})

window.todo.TaskCollection = Backbone.Collection.extend({
	url : appcontext + "webresources/tasks",
	model : window.todo.TaskModel

});

window.todo.TaskView = Backbone.View
		.extend({
			className : "todo-app",

			events : {
				"submit form" : "addActivity",
				"click .filter" : "filterActivity",
				"click .delete-item" : "deleteActivity",
				"dblclick .item" : "editActivity",
				"blur .item-input" : "hideActivityInput",
				"focusout .item-input" : "hideActivityInput",
				"keyup .item-input" : "hideActivityInput",
				"click .complete-task" : "completeActivity"
			},

			initialize : function(options) {
				this._itemTemplate = _
						.template('<li class="item" data-id=<%=itemId%>><div class="item-input invisible"><input type="text" class="form-control"></div><div class="item-display"><input type="checkbox" class="complete-task"><span class="item-desc"><%=itemText%></span><span class="delete-item pull-right">X</span></div></li>');
				this.render();
			},

			render : function() {
				var that = this;
				this.$el
						.append('<header>Todos</header><div class="content"><ul></ul></div>');
				this.options.$container.append(this.$el);
				this._$todoContainer = this.$el.find('.content ul');
				var newItem = '<li class="item new-item"><form><div class="form-group"><input type="text" class="form-control new-item-input" id="add-task" placeholder="Get milk, Walk the dog..."></div></form></li>';
				var status = '<div class="status">'
						+ '<span class="description"></span>'
						+ '<div class="filters">'
						+ '<a href="#all" class="filter active" data-filter="all">All</a>'
						+ '<a href="#active" class="filter" data-filter="active">Active</a>'
						+ '<a href="#completed" class="filter" data-filter="completed">Completed</a></div><span></span></div>';
				this._$todoContainer.append(newItem);
				this._$status = $(status);
				this._$todoContainer.after(this._$status);
				this.model.forEach(function(m) {
					that._renderActivity(m);
				})
			},

			addActivity : function(evt) {
				evt.preventDefault();
				evt.stopPropagation();
				var that = this;
				var input = this.$el.find('.new-item-input').val();
				if (!_.isEmpty(input)) {
					var activity = new window.todo.TaskModel();
					activity.set({
						description : input
					});
					activity.save().done(function() {
						that.model.add(activity);
						that._renderActivity(activity);
					});
				}
			},

			_renderActivity : function(activity) {
				var $item = $(this._itemTemplate({
					itemText : activity.get('description'),
					itemId : activity.get('id')
				}));
				this._$todoContainer.append($item);
				if (activity.get('status') === 'COMPLETE') {
					$item.find('[type="checkbox"]').prop('checked', true);
					$item.addClass('completed');
				}
				this.$el.find('.new-item-input').val('');
			},

			editActivity : function(evt) {
				var $item = this.$(evt.target).closest('.item');
				var text = $item.find('.item-display').addClass('invisible')
						.find('.item-desc').text();
				$item.find('.item-input').removeClass('invisible');
				$item.find('.item-input input').val(text).focus();
			},

			deleteActivity : function(evt) {
				var that = this;
				id = $(evt.target).closest('.item').data('id');
				var activity = this.model.get(id);
				activity.destroy().done(
						function() {
							that._$todoContainer.find(
									'[data-id=' + activity.get('id') + ']')
									.remove();
							that.model.remove(activity);
						});
			},

			completeActivity : function(evt) {
				var isChecked = $(evt.target).prop('checked');
				$(evt.target).closest('.item').toggleClass('completed',
						isChecked);
				if (isChecked) {
					var $item = this.$(evt.target).closest('.item');
					var id = $item.data('id');
					var activity = this.model.get(id);
					activity.set('status', 'COMPLETE');
					activity.save();
				}
			},

			filterActivity : function(evt) {
				this.$el
						.removeClass('filter-active filter-all filter-completed');
				var filter = $(evt.target).data('filter');
				this.$el.find('.filter').removeClass('active');
				switch (filter) {
				case "active":
					this.$el.addClass('filter-active');
					this.$el.find('[data-filter=active]').addClass('active');
					break;
				case "completed":
					this.$el.addClass('filter-completed');
					this.$el.find('[data-filter=completed]').addClass('active');
					break;
				default:
					this.$el.addClass('filter-all');
					this.$el.find('[data-filter=all]').addClass('active');
				}
				this.$el.add
			},

			hideActivityInput : function(evt) {
				if (evt.type === 'keyup' && evt.which !== 13) {
					return;
				}

				var $item = this.$(evt.target).closest('.item');
				var text = $item.find('.item-input input').val();

				var id = $item.data('id');
				var activity = this.model.get(id);
				activity.set('description', text);
				activity.save().then(function() {
					$item.find('.item-input').addClass('invisible');
					$item.find('.item-display .item-desc').text(text)
					$item.find('.item-display').removeClass('invisible');
				});
			}
		});