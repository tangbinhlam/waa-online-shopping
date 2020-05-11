$(document).ready(function() {
    $('.add-product-to-cart').click(function(event){
        event.preventDefault();
        var productId = $(this).attr("productId");

        $.ajax({
            //TODO: add link to configuration
            url: 'http://localhost:8080/rest/api/v1/carts/' + productId + '/' + 1,
            type: 'POST',
            dataType: "json",
            success: function (response) {
                console.log('add success')
            },
            error: function(xhr, status, error){
                alert(xhr.responseText);
            }
        });

    });
});
