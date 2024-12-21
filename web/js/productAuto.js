$(function () {
    $('#keyword').on('keyup', function () {
        var keyword = $(this).val();
        if (keyword !== '' && keyword !== null && keyword.length !== 0) {
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/WebProject_Web_exploded/productAuto?keyword=' + keyword,
                success: function (data) {
                    console.log(data);
                    var productListHTML = '';
                    for (var i = 0; i < data.length; i++) {
                        productListHTML += '<li class="productAutoItem" data-productId="';
                        productListHTML += data[i].productId;
                        productListHTML += '">';
                        productListHTML += data[i].categoryId;
                        productListHTML += ': ';
                        productListHTML += data[i].name;
                        productListHTML += '</li>';
                    }
                    $('#productAutoList').html(productListHTML);
                    $('#productAutoComplete').show();
                },
                error: function (errorMsg) {
                    console.log(errorMsg);
                }
            })
        } else {
            $('#productAutoComplete').hide();
        }
    })

    //该种事件绑定不可用，因为绑定时还未进行productAutoItem的渲染
    // $('.productAutoItem').on('click', function () {
    //
    // })

    $(document).on('click', '.productAutoItem', function () {
        var productId = $(this).data('productid');
        $('#productAutoComplete').hide();
        $('#keyword').val('');
        window.location.href = 'http://localhost:8080/WebProject_Web_exploded/productForm?productId=' + productId;
    })

    $('#SearchContent').on('mouseleave', function () {
        $('#productAutoComplete').hide();
        $('#keyword').val('');
    })


})