$(document).ready(function () {

    $('#form_id').submit(function (event) {
        event.preventDefault();
        event.stopImmediatePropagation();

        let username = $('#username').val();
        let email = $('#email').val();
        let password = $('#password').val();
        let role = "merchant";
        let blocked = $('#status').val() == 'Unblocked' ? false : true;

        $.post("/users/admin/create-merchant",
            {
                username: username,
                email: email,
                password: password,
                role: role,
                blocked: blocked
            })
            .done(
                function (data) {
                    let { username, blocked } = data;

                    let elem = `<div class="alert alert-success alert-dismissible fade show" role="alert"> Creation completed successfully! <br /> Summary: <ul class="list-group"> <li class="list-group-item list-group-item-success"> Username: ${username} </li> <li class="list-group-item list-group-item-success"> Blocked: ${blocked} </li> </ul> <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>`;

                    $('#message').append(elem);
                })
            .fail(
                function () {
                    let elem = '<div class="alert alert-danger alert-dismissible fade show" role="alert"> User already exists. <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>';

                    $('#message').append(elem);
                });

    });

});