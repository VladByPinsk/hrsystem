$(function() {
	$('#addMark').click(function() {
		var formValid = true;
		$('input').each(function() {
			var formGroup = $(this).parents('.skill');
			if (this.checkValidity()) {
				formGroup.addClass('has-success').removeClass('has-error');
			} else {
				formGroup.addClass('has-error').removeClass('has-success');
				formValid = false;
			}
		});
		if (formValid) {
			$('#success-alert').removeClass('hidden');
		}
	});
});