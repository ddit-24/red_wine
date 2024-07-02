$(function(){
    // 判断浏览器是否支持 placeholder

    function placeholderSupport() {
        return 'placeholder' in document.createElement('input');
    }

    if(!placeholderSupport()){
        $('[placeholder]').focus(function() {
            var input = $(this);
            if (input.val() == input.attr('placeholder')) {
                input.val('');
                input.removeClass('placeholder');
            }
        }).blur(function() {
            var input = $(this);
            if (input.val() == '' || input.val() == input.attr('placeholder')) {
                input.addClass('placeholder');
                input.val(input.attr('placeholder'));
            }
        }).blur();
    };


    //产品展示鼠标选中变色
    $(".manage_products_list li").bind('mouseenter',function(){
        $(this).removeClass('manage_products_unchecked').addClass('manage_products_checked');
    })
    $(".manage_products_list li").bind('mouseout',function(){
        $(this).removeClass('manage_products_checked').addClass('manage_products_unchecked');
    })


})
