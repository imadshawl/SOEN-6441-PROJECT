function search() {
   var searchText = document.getElementById("searchText").value;
   var r = jsRoutes.controllers.HomeController.search(searchText);
   $.ajax({url: r.url, type: r.type,
        success: function(data) {
            var resultNodeList = Array.from(document.querySelectorAll('.result')).map(node => node.innerHTML);
            console.log(resultNodeList);
            $("#result0").html(data);
            for (let i = 1; i < 10; i++) {
              $("#result".concat(i)).html(resultNodeList[i-1]);
            }
            $('#content').css('visibility', 'visible')
        },
        error:   function(err) {
            alert(err)
        }});
}