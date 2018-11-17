
function submit() {
    console.log("1");
    $.ajax({
        url: "/IoTPlatform/login",
        type: 'POST',
        data: {
            account: $('#inputEmail').val(),
            password: $('#inputPassword').val()
        },
        dataType: 'text',
        success: function (data) {
            console.log("OK");
            console.log(data);
            if (data == "OK") {
                window.location.href = "/IoTPlatform/page/dashboard";
            }
            else{

            }
        },
        error:function(data)
        {
            console.log("ERROR")
            console.log(data)
        }
    })
}