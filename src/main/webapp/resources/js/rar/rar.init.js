var Init = (function(window, document, undefined) {
    "use strict";

	//////////////////////
	// PUBLIC FUNCTIONS //
	//////////////////////

	var init = function() {
		$('#autocomplete').autocomplete({
			serviceUrl: Constants.BASE_URL + '/autocomplete/names',
			onSelect: function (suggestion) {
				window.location.href =  Constants.BASE_URL + "/customer/"+suggestion.data;
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