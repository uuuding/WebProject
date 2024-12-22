$(document).ready(function () {

    let submit = $('#submit');
    let msg = $('#msg');

    $('#username').on('input', function () {
        var username = $(this).val(); // 获取输入框的当前值
        // 检测用户名存在的函数
        checkUsernameExists(username, $(this), submit, msg);
    })}
)


function checkUsernameExists(username, input, submit, msg){

    $.ajax({
            type:'get',
            url:'Check',
            data:{username:username},
            dataType:'text',
            success: function (data) {
                // 根据返回的响应数据进行相应处理
                if (data === "true") {
                    input.css('border-color', 'red');
                    msg.html("用户名已存在");
                    msg.css('color', "red");
                    submit.prop('disabled', true);
                } else {
                    input.css('border-color', '#ccc');
                    msg.html("init your account");
                    msg.css("color", "initial");
                    submit.prop('disabled', false);
                }
            },
            error: function (xhr, status, error) {
                console.error("请求失败: ", status, error);
            }
        }


)




}
