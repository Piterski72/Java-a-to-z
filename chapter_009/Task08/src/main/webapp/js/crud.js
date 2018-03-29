/**
 * Created by pit on 04.03.2018.
 */

$(document).ready(function () {
    var table = $("#dtbl").DataTable();

    function view() {
        table.destroy();
        $.ajax({
            url: "usersview",
            method: "get",
            complete: function (data) {
                var myObj = JSON.parse(data.responseText);
                var arr = [];
                var record;
                var x;
                for (x in myObj) {
                    record = {
                        "ident": x,
                        "Name": myObj[x].name,
                        "Login": myObj[x].login,
                        "Password": myObj[x].password,
                        "Email": myObj[x].email,
                        "Role": myObj[x].role.name,
                        "City": myObj[x].city,
                        "Country": myObj[x].country,
                        "Create date": new Date(myObj[x].createDate).toLocaleString()
                    };
                    arr.push(record);
                }
                table = $("#dtbl").DataTable({
                    data: arr,
                    rowId: 'ident',
                    select: {
                        style: 'os',
                        selector: 'td:first-child'
                    },
                    autoWidth: false,
                    columns: [
                        {
                            data: null,
                            "defaultContent": "<input class= 'chk' type='checkbox'>",
                        },
                        {data: "ident"},
                        {data: "Name"},
                        {data: "Login"},
                        {data: "Password"},
                        {data: "Email"},
                        {data: "Role", width: "25%"},
                        {data: "City", width: "40%"},
                        {data: "Country", width: "40%"},
                        {data: "Create date"},
                        {
                            data: null,
                            "defaultContent": "<button class='btn-danger'>Delete <span class='glyphicon glyphicon-remove'></span></button>"
                        },
                        {
                            data: null,
                            "defaultContent": '<button class="btn-warning">Update <span class="glyphicon glyphicon-refresh"></span></button>'
                        }
                    ]
                });
            }
        });
    }

    view();

    //delete record

    $('#dtbl').on('click', 'button.btn-danger', function () {
        var rec = table.row($(this).parent()).data();
        $.post("delete", {"id": rec.ident}, function (data) {
            alert(data.result);
        });
        view();
    });

    //create new record

    $('#dtbl').on('click', 'button#add', function () {
        var nr = document.getElementById("tradd");
        var nrc = nr.getElementsByTagName("td");
        var record = {
            "name": nrc[2].innerHTML,
            "login": nrc[3].innerHTML,
            "password": nrc[4].innerHTML,
            "email": nrc[5].innerHTML,
            "rolename": nrc[6].getElementsByTagName('select')[0].value,
            "city": nrc[7].getElementsByTagName('input')[0].value,
            "country": nrc[8].getElementsByTagName('input')[0].value
        };
        if (validate(record)) {
            $.post("create", record, function (data) {
                alert(data.result);
            });
        }
        else {
            return;
        }
        locationList('cities', '#ci', '');
        locationList('countries', '#co', '');
        view();
    });

    //updating

    $('#dtbl').on('click', 'button.btn-warning', function () {
        var rowident = table.row($(this).parent()).id();
        var rowCurrent = document.getElementById(rowident);
        var nrc = rowCurrent.getElementsByTagName("td");
        var record = {
            "id": nrc[1].innerHTML,
            "name": nrc[2].innerHTML,
            "login": nrc[3].innerHTML,
            "password": nrc[4].innerHTML,
            "email": nrc[5].innerHTML,
            "rolename": nrc[6].getElementsByTagName('select')[0].value,
            "city": nrc[7].getElementsByTagName('input')[0].value,
            "country": nrc[8].getElementsByTagName('input')[0].value
        };
        if (validate(record)) {
            $.post("update", record, function (status) {
                alert("Status: " + status);
            });
        } else {
            return;
        }
        table.row($(this).parent()).draw();
        view();
    });

    //selection and un-select

    $('#dtbl').on('click', 'input.chk', function () {

        var tdata = table.row($(this).parent()).data();
        var rowDom = table.row($(this).parent()).node();
        var tds = rowDom.getElementsByTagName("td");
        var rowident = table.row($(this).parent()).id();
        var roleiden = "rolelist" + rowident;
        if (tds[0].getElementsByTagName('input')[0].checked) {
            for (var i = 2; i < tds.length - 6; i++) {
                tds[i].setAttribute('contenteditable', 'true');
            }
            var userrole = tds[6].innerHTML;
            var city = tds[7].innerHTML;
            var country = tds[8].innerHTML;

            tds[6].setAttribute('id', roleiden);
            roleList(("#" + roleiden), userrole);

            tds[7].setAttribute('id', ('city' + rowident));
            tds[8].setAttribute('id', ('country' + rowident));
            locationList('cities', ('#city' + rowident), city);
            locationList('countries', ('#country' + rowident), country);
        }
        else {
            //table.row($(this).parent()).invalidate().draw();
            table.row($(this).parent()).data(tdata);
        }
    });

    // validate non-empty

    function validate(data) {
        if (data.name != '' && data.login != '' && data.password != '' && data.email != '' && data.city != '' && data.country != '') {
            return true;
        } else {
            alert("empty input detected");
            return false
        }
    }

});

