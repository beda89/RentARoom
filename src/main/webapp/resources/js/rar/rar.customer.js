var Customer = (function(window, document, undefined) {
    "use strict";

    //////////////////////
    // PUBLIC FUNCTIONS //
    //////////////////////

    var init = function() {
        var requiredField = { validators: {notEmpty: {message: 'Pflichtfeld' }, stringLength: { min: 3, message: "Muss l&auml;nger als 3 Zeichen sein" } } };
        $('#add-customer-form')
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

        // Stornieren Buttons
        $('.cancel-reservation').click(function() {
            var id = $(this).parents("tr").attr("data-rar-reservation-id");
            reallyProceedModal(id, "Stornieren", "reservations/cancel/" + id);
        });

        // Checkout Buttons
        $('.checkout-reservation').click(function() {
            var id = $(this).parents("tr").attr("data-rar-reservation-id");
            reallyProceedModal(id, "Checkout", "reservations/checkout/" + id);
        });
    };

    var reallyProceedModal = function(id, submitCaption, url) {
        var modal = $("#really-proceed-modal");
        var submitBtn = modal.find('button[type="submit"]');
        var form = modal.find("form");
        form.attr("action", form.attr("action") + url);
        submitBtn.html(submitCaption);
        modal.modal();
    };

    return {
        init: init
    };

})(window, document);