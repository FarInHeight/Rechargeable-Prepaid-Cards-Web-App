$(document).ready(function () {

    $('#form_id').submit(function (event) {
        event.preventDefault();
        event.stopImmediatePropagation();

        let operation = $('#operation').val();

        if (operation == 'Charge') {

            $.post("/cards/merchant/subtract-credit",
                {
                    id: $('#card_id').val(),
                    amount: $('#amount').val(),
                    description: $('#description').val()
                })
                .done(
                    function (data) {
                        let { credit } = data;

                        let elem = `<div class="alert alert-success alert-dismissible fade show" role="alert"> Charge completed successfully! The new credit is &euro;${credit?.toFixed(2)}. <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>`;

                        $('#message').append(elem);
                    })
                .fail(
                    function (data) {
                        let elem = null;
                        let message = null;
                        let alertType = null;

                        if (data.status == 404) {
                            message = 'Invalid card number entered! Please check that you have entered the number correctly.';
                            alertType = 'alert-danger';
                        } else if (data.status == 405) {
                            message = 'The card is blocked! Please contact an administrator to unblock it.';
                            alertType = 'alert-warning';
                        } else {
                            message = 'Insufficient credit to perform the operation!';
                            alertType = 'alert-danger';
                        }

                        elem = `<div class="alert ${alertType} alert-dismissible fade show" role="alert"> ${message} <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>`;

                        $('#message').append(elem);
                    });

        } else {

            $.post("/cards/merchant/add-credit",
                {
                    id: $('#card_id').val(),
                    amount: $('#amount').val(),
                    description: $('#description').val()
                })
                .done(
                    function (data) {
                        let { credit } = data;

                        let elem = `<div class="alert alert-success alert-dismissible fade show" role="alert"> Recharge completed successfully! The new credit is &euro;${credit?.toFixed(2)}.<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>`;

                        $('#message').append(elem);
                    })
                .fail(function (data) {
                    let elem = null;
                    let message = null;
                    let alertType = null;

                    if (data.status == 404) {
                        message = 'Invalid card number entered! Please check that you have entered the number correctly.';
                        alertType = 'alert-danger';
                    } else {
                        message = 'The card is blocked! Please contact an administrator to unblock it.';
                        alertType = 'alert-warning';
                    }

                    elem = `<div class="alert ${alertType} alert-dismissible fade show" role="alert"> ${message} <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>`;

                    $('#message').append(elem);
                });

        }

    });

});