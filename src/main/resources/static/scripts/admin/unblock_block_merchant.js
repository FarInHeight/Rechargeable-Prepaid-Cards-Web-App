$(document).ready(function () {

    $('#form_id').submit(function (event) {
        event.preventDefault();
        event.stopImmediatePropagation();

        $.post("/users/admin/unblock-block-merchant",
            {
                username: $('#username').val()
            })
            .done(
                function (data) {
                    let { username, blocked } = data;

                    let elem = `<div class="alert alert-success alert-dismissible fade show" role="alert"> State flip completed successfully! <br /> Summary: <ul class="list-group"> <li class="list-group-item list-group-item-success"> Username: ${username} </li> <li class="list-group-item list-group-item-success"> Blocked: ${blocked} </li> </ul> <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>`;

                    $('#message').append(elem);
                })
            .fail(
                function () {
                    let elem = '<div class="alert alert-danger alert-dismissible fade show" role="alert"> Invalid username entered! Please check that you have entered a valid merchant username. <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>';

                    $('#message').append(elem);
                });
    });

});