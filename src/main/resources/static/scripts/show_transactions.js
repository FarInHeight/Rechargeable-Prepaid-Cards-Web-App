$(document).ready(function () {

    $('#reload').click(function (event) {
        event.preventDefault();
        event.stopImmediatePropagation();

        $.get("/transactions/get-transactions")
            .done(
                function (data) {
                    $('#tablebody').empty();

                    for (let transaction of data) {
                        
                        let { username, cardId, credit, description, creationTimestamp } = transaction;
                        creationTimestamp = new Date(Date.parse(creationTimestamp)).toLocaleString('it-IT');
                        
                        let row =   `<tr>
                                        <td> ${ username } </td>
                                        <td> ${ cardId } </td>
                                        <td> ${ credit?.toFixed(2) } </td>
                                        <td> ${ description } </td>
                                        <td> ${ creationTimestamp } </td>
                                    </tr>`;
                        $('#tablebody').append(row);

                    }

                });
    });

    $('#reload').click();

});