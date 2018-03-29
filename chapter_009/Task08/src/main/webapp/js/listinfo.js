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
                    if (roles[x].name == userrole) {
                        txt += "<option value=" + roles[x].name + " selected" + ">" + roles[x].name + "</option>";
                    } else {
                        txt += "<option value=" + roles[x].name + ">" + roles[x].name + "</option>";
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
function locationList(location, ident, selected) {
    $.ajax({
        url: "getinfo",
        method: "get",
        complete: function (data) {
            var myObj = JSON.parse(data.responseText);
            if (location == "cities") {
                location = JSON.parse(myObj.cities);
            }
            else if (location == "countries") {
                location = JSON.parse(myObj.countries);
            }
            if (location) {
                var txt = "<input class='form-control' list=" + ident + " value=" + selected + ">" + "<datalist id=" + ident + ">";
                var x;
                for (x in location) {
                    txt += "<option value=" + location[x] + ">";
                }
                txt += "</datalist>";
                $(ident).html(txt);
            } else {
                alert("error no data!");
            }
        }
    })
}











