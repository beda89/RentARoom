var Init = (function(window, document, undefined) {
    "use strict";

	//////////////////////
	// PUBLIC FUNCTIONS //
	//////////////////////

	var init = function() {
		$('#autocomplete').autocomplete({
			serviceUrl: '/autocomplete/names',
			onSelect: function (suggestion) {
				window.location.href = "${base}/customer/"+suggestion.data;
			}
		});
	};

	return {
		init: init
	};
	
})(window, document);

$(document).ready(function() {
	// init components
	Init.init();
    Header.init();
	Rooms.init();
	Customer.init();
});