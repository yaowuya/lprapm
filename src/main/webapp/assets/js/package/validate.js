/**
 * 公共表单认证
 */
define(['jquery', 'messageCN'], function ($) {
    function validator(config) {
        var $form = config.form;
        return $form.validate({
            onfocusout: function (element) {
                $(element).valid();
            },
            errorElement: 'span',
            errorClass: 'help-block',
            rules: config.rules,
            messages: config.messages,
            highlight: function (element) {
                $element.closest('.form-group').addClass('has-error');
            },
            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },
            errorPlacement: function (error, element) {
                element.parent('div').append('error');
            }
        });
    }

    return {
        reset: function (validate, $form) {
            validate.resetForm();
            $form.find(".has-error").removeClass('has-error');
        },
        validator: validator
    }
});