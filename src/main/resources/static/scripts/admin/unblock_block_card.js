$(document).ready(function () {

    $('#form_id').submit(function (event) {
        event.preventDefault();
        event.stopImmediatePropagation();

        $.post("/cards/admin/unblock-block-card",
            {
                id: $('#card_id').val()
            })
            .done(
                function (data) {
                    let { blocked, credit } = data;

                    let elem = `<div class="alert alert-success alert-dismissible fade show" role="alert"> State flip completed successfully! <br /> Summary: <ul class="list-group"> <li class="list-group-item list-group-item-success"> Blocked: ${blocked}</li> <li class="list-group-item list-group-item-success"> Credit: &euro;${credit?.toFixed(2)} </li> </ul><button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>`;

                    $('#message').append(elem);
                })
            .fail(
                function () {
                    let elem = '<div class="alert alert-danger alert-dismissible fade show" role="alert"> Invalid card number entered! Please check that you have entered the number correctly.  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>';

                    $('#message').append(elem);
                });
    });

});