$("#type").change(function() {

	switch ($('#type').val()) {

	case "applicant":
		$('#for-applicant').removeClass('hidden');
		$('#for-hr').addClass('hidden');
		break;

	case "hr":
		$('#for-applicant').addClass('hidden');
		$('#for-hr').removeClass('hidden');
		break;

	}

});