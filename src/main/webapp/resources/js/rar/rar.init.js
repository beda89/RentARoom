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
    Header.init();
	Rooms.init();
});