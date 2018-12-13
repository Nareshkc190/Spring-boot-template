var txt = "";
var x = 0;
var y = 1;
var text = document.getElementById("demo").innerHTML;
function loadDoc() {
    $.ajax({
        url: '/api/products',
        type: "GET",
        dataType: "json",
        success: function (data) {
            console.log(data);
            txt = "";
            for (x in data) {
                txt +=  y++  + " " +  data[x].name + "<br/>";
            }
            if(text != txt) {
                text = txt;
            }

        },
        fail: function () {
            console.log("Encountered an error")
        }
    });
}

function changeColor(){
    document.getElementById("demo").blur();
}