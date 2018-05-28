/**
 * Created by pit on 04.03.2018.
 */

$(document).ready(function () {
    var table = $("#dtbl").DataTable();

    /**
     * view all records
     */
    function view() {
        table.destroy();
        $.ajax({
            url: "usersview",
            method: "get",
            complete: function (data) {
                var myObj = JSON.parse(data.responseText);
                console.log(myObj);
                var arr = [];
                var record;
                var x;
                var y;
                for (x in myObj) {
                    for (y in myObj[x]) {
                        record = {
                            "ident": myObj[x][0],
                            "Name": myObj[x][1],
                            "Address": myObj[x][2],
                            "Music": myObj[x][4],
                            "Role": myObj[x][3],
                        };
                    }
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
                        {data: "Address"},
                        {data: "Music"},
                        {data: "Role"},
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

    /**
     * delete record
     */
    $('#dtbl').on('click', 'button.btn-danger', function () {
        var rec = table.row($(this).parent()).data();
        $.post("delete", {"id": rec.ident}, function (data) {
            alert(data.result);
        });
        view();
    });

    /**
     * create new record
     */
    $('#dtbl').on('click', 'button#add', function () {
        var nr = document.getElementById("tradd");
        var nrc = nr.getElementsByTagName("td");
        var record = {
            "name": nrc[2].innerHTML,
            "address": nrc[3].innerHTML,
            "music": nrc[4].innerHTML,
            "rolename": nrc[5].getElementsByTagName('select')[0].value,

        };
        if (validate(record)) {
            $.post("create", record, function (data) {
                alert(data.result);
            });
        }
        else {
            return;
        }
        view();
    });

    /**
     * updating
     */
    $('#dtbl').on('click', 'button.btn-warning', function () {
        var rowident = table.row($(this).parent()).id();
        var rowCurrent = document.getElementById(rowident);
        var nrc = rowCurrent.getElementsByTagName("td");
        var record = {
            "id": nrc[1].innerHTML,
            "name": nrc[2].innerHTML,
            "address": nrc[3].innerHTML,
            "music": nrc[4].innerHTML,
            "rolename": nrc[5].getElementsByTagName('select')[0].value,
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

    /**
     * selection and un-select
     */
    $('#dtbl').on('click', 'input.chk', function () {

        var tdata = table.row($(this).parent()).data();
        var rowDom = table.row($(this).parent()).node();
        var tds = rowDom.getElementsByTagName("td");
        var rowident = table.row($(this).parent()).id();
        var roleiden = "rolelist" + rowident;
        if (tds[0].getElementsByTagName('input')[0].checked) {
            for (var i = 2; i < 6; i++) {
                tds[i].setAttribute('contenteditable', 'true');
            }
            var userrole = tds[5].innerHTML;
            tds[5].setAttribute('id', roleiden);
            roleList(("#" + roleiden), userrole);
        }
        else {
            //table.row($(this).parent()).invalidate().draw();
            table.row($(this).parent()).data(tdata);
        }
    });

    /**
     * validate non-empty
     * @param data
     * @returns {boolean}
     */
    function validate(data) {
        if (data.name != '' && data.address != '' && data.music != '' && data.rolename != '') {
            return true;
        } else {
            alert("empty input detected");
            return false
        }
    }

    /**
     * view filtered data
     * @param data
     */
    function viewfilter(data) {
        table.destroy();
        $.ajax({
            url: "usersview",
            method: "post",
            data,
            complete: function (data) {
                var myObj = JSON.parse(data.responseText);
                var arr = [];
                var record;
                var x;
                var y;
                for (x in myObj) {
                    for (y in myObj[x]) {
                        record = {
                            "ident": myObj[x][0],
                            "Name": myObj[x][1],
                            "Address": myObj[x][2],
                            "Music": myObj[x][4],
                            "Role": myObj[x][3],
                        };
                    }
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
                        {data: "Address"},
                        {data: "Music"},
                        {data: "Role"},
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

    /**
     * view filtered by address
     */
    $("#addr").click(function () {
        var address = document.getElementById("addrinput");
        var data = {"address": address.value, "rolename": "", "music": ""};
        viewfilter(data);
    });

    /**
     * view filtered by role
     */
    $("#rol").click(function () {
        var role = document.getElementById("rlinput");
        var data = {"address": "", "rolename": role.value, "music": ""};
        viewfilter(data);
    });

    /**
     * view filtered by music
     */
    $("#mus").click(function () {
        var music = document.getElementById("musinput");
        var data = {"address": "", "rolename": "", "music": music.value};
        viewfilter(data);
    });
    /**
     * view all records
     */
    $("#all").click(function () {
        view();
    });

});

