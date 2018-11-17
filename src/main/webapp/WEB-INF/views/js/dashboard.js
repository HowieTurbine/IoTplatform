$(document).ready(function () {
    $('#result').html("<br>");
    $.ajax({
        url: "/IoTPlatform/all",
        type: 'GET',
        data: {},
        dataType: 'json',
        success: function (data) {
            if (data.status === "OK") {
                $('#te').val(data.time + "s");
                var HTML = "";
                HTML += "<table class=\"table table-striped table-sm\">";
                var title = true;
                $.each(data.result, function (index, row) {
                    if (title)
                        HTML += "<thead>";
                    HTML += "<tr>";
                    $.each(row, function (index, element) {
                        HTML += "<td>";
                        HTML += element;
                        HTML += "</td>"
                    });
                    if (title) {
                        HTML += "<td>";
                        HTML += "Switch Free Mode";
                        HTML += "</td>"
                    }
                    else {
                        var id = row[0];
                        var isFree = row[4];
                        HTML += "<td>";
                        if (isFree == "0")
                            HTML += "<button type=\"button\" onclick='changeFreeMode("+id+",1);'>Open Free Model</button>";
                        else
                            HTML += "<button type=\"button\" onclick='changeFreeMode("+id+",0);'>Close Free Model</button>";
                        HTML += "</td>"
                    }
                    HTML += "</tr>";
                    if (title) {
                        HTML += "</thead>";
                        HTML += "<tbody>";
                    }
                    title = false;
                });
                HTML += "</tbody>";
                HTML += "</table>";
                $('#result').append(HTML);
            }
            else if (data.status === "ERROR") {
                $('#result').append(data.result);
            }
        },
        error: function (request, error) {
            alert("Request: " + JSON.stringify(request));
        }
    });
});

function changeFreeMode(id,x){
    console.log(id);
    console.log(x);
    $.ajax({
        url: "/IoTPlatform/freeMode",
        type: 'POST',
        data: {
            value:x,
            id:id
        },
        dataType: 'text',
        success: function (data) {
            console.log("OK");
            console.log(data);
            if (data == "SUCCESS") {
                window.location.reload(true);
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

function refreshPage() {
    window.location.reload(true);
}
