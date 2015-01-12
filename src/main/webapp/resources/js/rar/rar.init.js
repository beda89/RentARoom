var Init = (function(window, document, undefined) {
    "use strict";

	//////////////////////
	// PUBLIC FUNCTIONS //
	//////////////////////

	var init = function() {

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