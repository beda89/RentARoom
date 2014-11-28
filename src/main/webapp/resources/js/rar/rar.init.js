var Init = (function(window, document, undefined) {
    "use strict";
	
	return {};
	
})(window, document);

$(document).ready(function() {
	// cache jQuery vars for global access
	var $editRoom = $(document.getElementById('edit-room'));
	var $reserveRoom = $(document.getElementById('reserve-room'));
	
	// init components
    Header.init();
});