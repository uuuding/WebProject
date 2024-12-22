$(document).ready(function () {
    const $tooltip = $('#tooltip'); // 悬浮窗 DOM 元素

    $('.hover-image').on('mouseenter', function (event) {
        const imageId = $(this).data('id'); // 获取图片 ID

        // AJAX 请求后端，获取提示信息
        $.ajax({
            url: 'showInfo?id=' + imageId,// 后端接口地址
            method: 'GET',
            data: { id: imageId },
            dataType: 'json',
            success: function (response) {
                if (response.success) {
                    $tooltip
                        .text(response.message) // 设置提示信息
                        .css({
                            top: event.pageY + 10 + 'px',
                            left: event.pageX + 10 + 'px',
                        })
                        .fadeIn(200); // 显示悬浮窗
                } else {
                    $tooltip.text('未找到提示信息').fadeIn(200);
                }
            },
            error: function () {
                $tooltip.text('服务器错误').fadeIn(200);
            },
        });
    });

    // 鼠标移动时更新悬浮窗位置
    $('.hover-image').on('mousemove', function (event) {
        $tooltip.css({
            top: event.pageY + 10 + 'px',
            left: event.pageX + 10 + 'px',
        });
    });

    // 鼠标移出时隐藏悬浮窗
    $('.hover-image').on('mouseleave', function () {
        $tooltip.fadeOut(200);
    });
});
