$(function() {
	$('#add-education').click(function() {
		var formValid = true;
		$('#form-education input').each(function() {
			var formGroup = $(this).parents('.form-group');
			if (this.checkValidity()) {
				formGroup.addClass('has-success').removeClass('has-error');
			} else {
				formGroup.addClass('has-error').removeClass('has-success');
				formValid = false;
			}
		});
	});
});