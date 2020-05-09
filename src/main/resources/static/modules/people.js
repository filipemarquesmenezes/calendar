$(function() {
    $("form button").click(function() {
        alert("submitted");
        var data = JSON.stringify( $("form").serialize() ); //  <-----------
        console.log( data );

         $.ajax({
                url: "/people",
                type: "post",
                dataType: "json",
                data: JSON.stringify($("form").serializeArray()[0]),
                contentType: "application/json",
                success: function(data) {
                    console.log(data);
                }
           });

         return false; //don't submit
    });


});