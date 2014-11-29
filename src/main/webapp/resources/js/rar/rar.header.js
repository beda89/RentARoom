var Header = (function(window, document, undefined) {
    "use strict";

	///////////////////////
    // PRIVATE FUNCTIONS //
    ///////////////////////
	
    var _initCustomerSearch = function() {
		var $customerSearch = $(document.getElementById('customer-search'));
		var $customerSearchResult = $(document.getElementById('customer-search-result'));
		$customerSearch.focus(function() {
			var rect = this.getBoundingClientRect();
			$customerSearchResult.fadeIn('fast');
			$customerSearchResult.css({
				top: rect.top + rect.height - 1,
				left: rect.left,
				width: rect.width
			});
		}).focusout(function() {
			$customerSearchResult.fadeOut('fast');
		});
	};

    //////////////////////
    // PUBLIC FUNCTIONS //
    //////////////////////

    var init = function() {
		_initCustomerSearch();
    };

    return {
        init: init
    };

})(window, document);
