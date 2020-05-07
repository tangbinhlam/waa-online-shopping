jQuery(document).ready(function($) {
    $("#productForm").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        let bookJSON = JSON.stringify($("#productForm").serializeFormJSON());
        console.log("dafata" + bookJSON);
        $.ajax({
            type: "POST",
            url: `http://localhost:8080/buyer/carts/${bookJSON.productId}/add`,
            data: bookJSON,
            contentType: "application/json",
            dataType: "json",
            success: function(result){
                console.log('success');
                console.log(result);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log('error');
                let errorObj = XMLHttpRequest.responseJSON;
                if(errorObj.errorType === 'ValidationError'){
                    let errorArray = errorObj.fieldErrors;

                    let errorMessage = "<p>";

                    $.each(errorArray, function(index, e){
                        errorMessage = errorMessage + e.field +" : " + e.message;
                    });
                    errorMessage += "</p>";

                    $('#result').append(errorMessage);

                }else{
                    alert("error happened not because of validation")
                }


            }
        });

    });
});
