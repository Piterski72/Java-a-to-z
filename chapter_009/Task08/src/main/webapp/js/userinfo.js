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
                var lgn = myObj.login;
                var rl = myObj.rolename;
                $("#ulog").html(lgn);
                $("#urole").html(rl);
                if (rl.toString() != "admin") {
                    $(".admin").hide();
                }
            } else {
                alert("error!");
            }
        }
    });
});


