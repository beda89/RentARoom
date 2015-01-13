var Reservation = (function(window, document, undefined) {
    "use strict";

    //////////////////////
    // PUBLIC FUNCTIONS //
    //////////////////////

    var init = function() {
        var requiredField = { validators: {notEmpty: {message: 'Pflichtfeld' }, stringLength: { min: 3, message: "Muss l&auml;nger als 3 Zeichen sein" } } };
        $('#createCustomer')
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
                },
                fields: {
                    firstName: requiredField,
                    lastName: requiredField,
                    address: requiredField,
                    phone: { validators: { notEmpty: {message: 'Pflichtfeld' }, phone: { country: "DE", message: "Keine g&uuml;ltige Telefonnummer" }}},
                    mail: { validators: { notEmpty: {message: 'Pflichtfeld' }, emailAddress: { message: "Keine g&uuml;ltige E-Mail Adresse" }}}
                }
            });

        $('div.radio label').click(function() {
            var otherRadioBtn = $('div.radio label').not($(this));
            otherRadioBtn.toggle();
            otherRadioBtn.parents('div.panel').find('div.panel-body').find('input, textarea').attr('disabled', 'disabled');
            $(this).parents('div.panel').find('div.panel-body').find('input, textarea').removeAttr('disabled');
        });

        $('#next').click(function() {
            if ($('#optionsRadios1').prop('checked')) {
                // Bestehender Kunde
                $('#existingCustomer').submit();
            } else {
                // Neuer Kunde
                $('#createCustomer').submit();
            }
        });

        // disable form for new customer on site load
        $('div.panel').last().find('div.panel-body').find('input, textarea').attr('disabled', 'disabled');
    };

    return {
        init: init
    };

})(window, document);