/**
 * Created by pit on 13.02.2018.
 */

$(document).ready(function () {
    $.ajax({
        url: "getinfo",
        method: "get",
        complete: function (data) {
            var myObj = JSON.parse(data.responseText);
            if (myObj) {
                var rl = myObj.rolename;
                $("#urole").html(rl);
                if (rl.toString().toLowerCase() != "admin") {
                    $(".admin").hide();
                }
            } else {
                alert("error!");
            }
        }
    });
});


