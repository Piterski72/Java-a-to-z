/**
 * Created by pit on 18.03.2018.
 */

$(document).ready(function () {

    var roletable = $("#rltable").DataTable();

    //view roles
    function roleview() {
        roletable.destroy();
        $.ajax({
            url: "getinfo",
            method: "get",
            complete: function (data) {
                var myObj = JSON.parse(data.responseText);
                var roles = JSON.parse(myObj.roles);
                var arr = [];
                var record;
                var x;
                for (x in roles) {
                    if (roles[x].name != 'admin') {
                        record = {
                            "name": roles[x].name
                        };
                        arr.push(record)
                    }
                }
                roletable = $("#rltable").DataTable({
                    "dom": 'rit',
                    data: arr,
                    columns: [
                        {data: "name", width: "10%"},
                        {
                            data: null,
                            width: "10%",
                            "defaultContent": "<button class='rldel'>Delete <span class='glyphicon glyphicon-remove'></button>"
                        }
                    ]
                });
            }
        });
    }

    roleview();

    //add new role
    roletable.on('click', 'button#addrole', function () {
        var rolename = document.getElementById("rlinput").value;
        if (rolename != '') {
            var record = {"rolename": rolename};
            $.post("newrole", record, function (data) {
                alert(data.result);
            });
        }
        roleview();
    });

    //delete new role
    roletable.on('click', 'button.rldel', function () {
        var rec = roletable.row($(this).parent()).data();
        $.post("deleterole", {"rolename": rec.name}, function (data) {
            alert(data.result);
        });
        roleview();
    });
});







