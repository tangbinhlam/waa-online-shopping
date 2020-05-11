$(document).ready(function() {
    $('.order-print-btn').on('click', function() {
        $(".tool-bar").hide();
        $(".header-text").hide();
        $(".order-print-btn").hide();
        window.print();
        $(".tool-bar").show();
        $(".header-text").show();
        $(".order-print-btn").show();
    });
});
