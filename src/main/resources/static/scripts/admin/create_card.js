$(document).ready(function () {

    $('#form_id').submit(function (event) {
        event.preventDefault();
        event.stopImmediatePropagation();

        let blocked = $('#status').val() == 'Unblocked' ? false : true;
        let credit = $('#credit').val();

        $.post("/cards/admin/create-card",
            {
                blocked: blocked,
                credit: credit
            })
            .done(
                function (id) {
                    let elem = `<div class="alert alert-success alert-dismissible fade show" role="alert"> Creation completed successfully! <br /> The newly created card has a number equal to ${id}. <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>`;

                    $('#message').append(elem);
                })
            .fail(
                function () {
                    let elem = '<div class="alert alert-danger alert-dismissible fade show" role="alert"> An error occured while creating the card. <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>';

                    $('#message').append(elem);
                });

    });

});