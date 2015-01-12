var Rooms = (function(window, document, undefined) {
    "use strict";

    //////////////////////
    // PUBLIC FUNCTIONS //
    //////////////////////

    var init = function() {
        $('#edit-rooms')
            // IMPORTANT: You must declare .on('init.field.bv')
            // before calling .bootstrapValidator(options)
            .on('init.field.bv', function(e, data) {
                // data.bv      --> The BootstrapValidator instance
                // data.field   --> The field name
                // data.element --> The field element

                var $parent = data.element.parents('.form-group'),
                    $icon   = $parent.find('.form-control-feedback[data-bv-icon-for="' + data.field + '"]');

                // From v0.5.3, you can retrieve the icon element by
                // $icon = data.element.data('bv.icon');

                $icon.on('click.clearing', function() {
                    // Check if the field is valid or not via the icon class
                    if ($icon.hasClass('glyphicon-remove')) {
                        // Clear the field
                        data.bv.resetField(data.element);
                    }
                });
            })

            .bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                }
            });

        $('.edit-room').click(function() {
            var id = $(this).parents("tr").attr("data-rar-room-id");
            $.get("/rooms/" + id, null, function(data) {
                var modal = $('#edit-rooms');
                var form = modal.find("form");
                form.attr("action", form.attr("action") + id);

                // insert data from room into editable form
                for (var key in data) {
                    form.find('input[name="' + key + '"]').val(data[key]);
                }

                // submit button
                modal.find('button[type="submit"]').click(function() {
                    var data = {};
                    var inputs = form.find('input.form-control, input[name="_csrf"]');
                    inputs.each(function(i) {
                        data[$(inputs[i]).attr("name")] = $(inputs[i]).val();
                    });
                    //console.log(data);
                    $.post("/rooms/" + id, data, function(data) {
                        modal.modal('hide');
                    }).fail(function(e) {
                        console.error(e);
                    });
                    return false;
                });

                // show modal dialog
                modal.modal();
            }).fail(function(e) {
                console.error(e);
            });;
        });
    };

    return {
        init: init
    };

})(window, document);
