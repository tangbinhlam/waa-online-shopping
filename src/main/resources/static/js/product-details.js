$(document).ready(function() {
    $(".qtyminus").on("click",function(){
        var now = $(".qty").val();
        if ($.isNumeric(now)){
            if (parseInt(now) -1> 0)
            { now--;}
            $(".qty").val(now);
        }
    })
    $(".qtyplus").on("click",function(){
        var now = $(".qty").val();
        if ($.isNumeric(now)){
            $(".qty").val(parseInt(now)+1);
        }
    });

    $('.add-product-to-cart').click(function(event){
        event.preventDefault();
        var productId = $(this).attr("productId");
        var quantity = $(".qty").val();

        $.ajax({
            //TODO: add link to configuration
            url: 'http://localhost:8080/rest/api/v1/carts/' + productId + '/' + quantity,
            type: 'POST',
            dataType: "json",
            success: function (response) {
                location.reload(true);
            },
            error: function(xhr, status, error){
                alert(xhr.responseText);
            }
        });

    });
});
