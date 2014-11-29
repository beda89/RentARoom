var Rooms = (function(window, document, undefined) {
    "use strict";

    //////////////////////
    // PUBLIC FUNCTIONS //
    //////////////////////

    var init = function() {
        var $editRoom = $(document.getElementById('edit-room'));
        var $reserveRoom = $(document.getElementById('reserve-room'));
        $('#roomgrid').find('button.btn').click(function() {
            $(this).toggleClass('active');
            var countSelectedRooms = $('#roomgrid button.btn.active').length;
            if (countSelectedRooms != 1) {
                $editRoom.addClass('disabled');
            } else {
                $editRoom.removeClass('disabled');
            }
            if (countSelectedRooms > 0) {
                $reserveRoom.removeClass('disabled');
            } else {
                $reserveRoom.addClass('disabled');
            }
        });
    };

    return {
        init: init
    };

})(window, document);