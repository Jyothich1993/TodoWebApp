$(function() {
	var activities = new window.todo.TaskCollection();
	var $container = $('.todo-app-container');
	activities.fetch().done(function(data) {
		new window.todo.TaskView({
			model : activities,
			$container : $container
		});
	});
});