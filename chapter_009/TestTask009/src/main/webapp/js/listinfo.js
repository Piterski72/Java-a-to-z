/**
 * Created by pit on 22.02.2018.
 */

function roleList(idstring, userrole) {

    $.ajax({
        url: "getinfo",
        method: "get",
        complete: function (data) {
            var myObj = JSON.parse(data.responseText);
            var roles = JSON.parse(myObj.roles);
            if (roles) {
                var x;
                var txt = "<select class='form-control' name='rolename'>";
                for (x in roles) {
                    if (roles[x].rolename.toString().toLowerCase() == userrole.toString().toLowerCase()) {
                        txt += "<option value=" + roles[x].rolename + " selected" + ">" + roles[x].rolename + "</option>";
                    } else {
                        txt += "<option value=" + roles[x].rolename + ">" + roles[x].rolename + "</option>";
                    }
                }
                txt += "</select>";
                $(idstring).html(txt);
            } else {
                alert("error no data!");
            }
        }
    });
}











