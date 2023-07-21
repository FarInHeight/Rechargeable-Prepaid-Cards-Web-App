$(document).ready(function () {

    $('#form_id').submit(function (event) {
        event.preventDefault();
        event.stopImmediatePropagation();

        $.post("/cards/check-credit",
            {
                id: $('#card_id').val()
            })
            .done(
                function (data) {
                    let { credit } = data;

                    let elem = `<div class="alert alert-success alert-dismissible fade show" role="alert"> The credit card is &euro;${credit?.toFixed(2)}. <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>`;

                    $('#message').append(elem);
                })
            .fail(
                function () {
                    let elem = '<div class="alert alert-danger alert-dismissible fade show" role="alert"> Invalid card number entered! Please check that you have entered the number correctly.  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>';

                    $('#message').append(elem);
                });
    });

});