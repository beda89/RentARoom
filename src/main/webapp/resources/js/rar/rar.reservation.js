var Reservation = (function(window, document, undefined) {
    "use strict";

    //////////////////////
    // PUBLIC FUNCTIONS //
    //////////////////////

    var init = function() {
        $('div.radio').click(function() {
            var otherRadioBtn = $('div.radio').not($(this));
            otherRadioBtn.parents('div.panel').find('div.panel-body').find('input, textarea').attr('disabled', 'disabled');
            $(this).parents('div.panel').find('div.panel-body').find('input, textarea').removeAttr('disabled');
        });

        // disable form for new customer on site load
        $('div.panel').last().find('div.panel-body').find('input[type="text"], textarea').attr('disabled', 'disabled');
    };

    return {
        init: init
    };

})(window, document);