$(document).ready(function() {

    $('.product-remove-btn').click(function(event){
        event.preventDefault();
        var productId = $(this).attr("data");

        $.ajax({
            //TODO: add link to configuration
            url: 'http://localhost:8080/rest/api/v1/products/' + productId,
            type: 'DELETE',
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
