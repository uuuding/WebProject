$(function () {

    // 页面加载时更新购物车
    updateCart();
    // 页面加载时初始化已选商品计数
    updateSelectedCount();

    // 数量修改功能
    $('table').on('change', '.item-quantity', function () {
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
                alert('更新购物车时发生错误，请稍后重试。');
            }
        });
    });

    // 点击减少按钮
    $('table').off('click', '.quantity-decrease').on('click', '.quantity-decrease', function () {
        var itemId = $(this).data('item-id'); // 获取商品 ID
        var quantityInput = $('#item-quantity-' + itemId); // 获取数量输入框
        var currentQuantity = parseInt(quantityInput.val()); // 获取当前数量

        currentQuantity--; // 减少数量

        // 更新数量输入框的值
        quantityInput.val(currentQuantity);
        // 触发 change 事件更新购物车
        quantityInput.trigger('change');
    });
    // 点击增加按钮
    $('table').off('click', '.quantity-increase').on('click', '.quantity-increase', function () {
        var itemId = $(this).data('item-id'); // 获取商品 ID
        var quantityInput = $('#item-quantity-' + itemId); // 获取数量输入框
        var currentQuantity = parseInt(quantityInput.val()); // 获取当前数量

        currentQuantity++; // 增加数量

        // 更新数量输入框的值
        quantityInput.val(currentQuantity);
        // 触发 change事件更新购物车
        quantityInput.trigger('change');
    });

    // 删除商品功能
    $('table').on('click', '#remove-item', function () {
        var itemId = $(this).data('item-id'); // 获取商品 ID
        $(`#rank-${itemId}`).animate({
            opacity: 0,
            paddingLeft: '+=180px'
        }, 500, 'swing', function () {
            $(this).remove(); // 移除商品行
            updateCart(); // 更新购物车状态
        });

        // 同步更新服务器端购物车
        $.ajax({
            type: 'POST',
            url: 'updateAuto',
            data: {itemId, quantity: 0}, // 设置数量为 0 表示删除
            error: function () {
                alert('删除商品时发生错误，请稍后重试。');
            }
        });
    });

    // 全选功能
    $('#select-all').on('change', function () {
        var isChecked = $(this).is(':checked'); // 检查全选框状态
        $('.item-select').prop('checked', isChecked); // 设置所有商品选择状态
        updateSelectedCount(); // 更新已选商品计数
    });

    // 单个商品选择功能
    $('table').on('change', '.item-select', function () {
        var totalItems = $('.item-select').length; // 商品总数
        var selectedItems = $('.item-select:checked').length; // 已选商品数

        // 如果已选商品数等于商品总数，则勾选全选框，否则取消勾选
        $('#select-all').prop('checked', totalItems === selectedItems);
        updateSelectedCount(); // 更新已选商品计数
    });

    // 更新购物车状态
    function updateCart() {
        var totalItems = $('.item-id').length; // 获取购物车中商品行数

        // 如果购物车为空，显示提示 不显示summary
        if (totalItems === 0) {
            $('#empty-cart-message').show();
            $('#cart-summary').hide();
        } else {
            $('#empty-cart-message').hide();
            $('#cart-summary').show();
        }

        updateSelectedCount(); // 更新已选商品计数
    }

    // 更新已选商品计数
    function updateSelectedCount() {
        var selectedCount = $('.item-select:checked').length; // 获取已选商品数
        $('#selected-count').text(selectedCount); // 更新显示
    }
});
