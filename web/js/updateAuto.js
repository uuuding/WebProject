$(function () {

    // 页面加载时更新购物车
    updateCart();

    $('table').on('change', '#item-quantity', function () {
        var itemId = $(this).data('item-id'); // 获取商品 ID
        var quantity = $(this).val(); // 获取输入的数量
        if (quantity === '0') {
            // 数量为0，删除对应的行
            $('#rank-' + itemId).animate({
                opacity: 0.0,
                paddingLeft: '+=180px'
            }, 500, 'swing', function () {
                $(this).remove();
                updateCart();
            });
        }
        $.ajax({
            type: 'POST',
            url: 'updateAuto',
            data: {itemId: itemId, quantity: quantity},
            dataType: 'json',
            success: function (response) {

                var formattedTotalPrice = '$' + response.totalPrice.toFixed(2);
                var formattedSubTotal = '$' + response.subTotal.toFixed(2);

                // 更新商品的总价
                $('#total-price-' + itemId).text(formattedTotalPrice);
                // 更新购物车小计
                $('#subTotal').text(formattedSubTotal);

            },
            error: function () {
                alert('An error occurred while updating the cart.');
            }
        });
    });


    // 更新购物车状态，如果没有商品则显示购物车为空提示
    function updateCart() {
        var totalItems = $('.item-id').length; // 获取购物车中商品的行数

        // 如果购物车没有商品，显示空购物车提示
        if (totalItems === 0) {
            // 显示购物车为空的提示行
            $('#empty-cart-message').show();
        } else {
            // 如果购物车有商品，隐藏提示行
            $('#empty-cart-message').hide();
        }
    }

});
