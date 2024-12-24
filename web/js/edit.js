$(document).ready(function (){

    let password = $('#password');
    let repeat = $('#repeat');

    repeat.on('input', function (){
        checkRepeat(password.val(), $(this));
    });

    password.on('input', function (){
        checkRepeat($(this).val(), repeat);
    });



})


function checkRepeat(password, repeat){

    if(repeat.val() != null && password != null) {

        if (password !== repeat.val()) {
            repeat.css('border-color', 'red');
            return;
        }

    }

    repeat.css('border-color', '#ccc');

}