
$(document).ready(function () {
    let a = document.querySelector("#test");
    a.addEventListener('click', function () {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/PolyBrain/booking/list',
            data: null,
            dataType: 'json',
            success: function (response) {
                console.log(response);

                },
            error: function (error) {
                alert(error.status);
            }

        });
    });
});