define(['ajaxPackage', 'messageCN'], function(AjaxPackage) {
    // 纯数字验证
    jQuery.validator.addMethod("onlynum", function(value, element) {
        var tel = /([\u4e00-\u9fa5a-zA-Z]+[0-9]*)/;
        return this.optional(element) || (tel.test(value));
    }, "不能为纯数字");
    // 手机号码验证
    jQuery.validator
        .addMethod(
            "isMobile",
            function(value, element) {
                var length = value.length;
                return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/
                    .test(value));
            }, "请正确填写您的手机号码。");
        // 注册验证
    $("#registerForm").validate({
        submitHandler: function(form) {
            var params = $("#registerForm").serializeArray();
            AjaxPackage.Ajax.request({
                // http://localhost:8080
                url: "/user/register",
                type: 'post',
                data: params,
                success: function(response) {
                    if (response.success) {
                        window.location.href = "../../../login.html";
                    } else {
                        console.log(response);
                    }
                }
            });
        },
        errorPlacement: function(error, element) {
            if (element.attr("id") == "agree") {
                $(element).parent('label').addClass('error');
            } else {
                $(element).after(error);
            }
        },
        errorElement: "p",
        rules: {
            userEmail: {
                required: true,
                email: true
            },
            userPassword: {
                required: true,
                rangelength: [5, 12]
            },
            userPasswordEnsure: {
                required: true,
                rangelength: [5, 12],
                equalTo: "#userPassword"
            },
            userTrueName: {
                required: true,
                onlynum: true
            },
            userPhone: {
                required: true,
                isMobile: true
            },
            agree: {
                required: true
            }
        },
        messages: {
            userEmail: {
                required: "账号不能为空",
                email: "请输入正确的邮箱格式"
            },
            userPassword: {
                required: "密码不能为空",
                rangelength: "密码长度不能少于5位不能多于12位"
            },
            userPasswordEnsure: {
                required: "确认密码不能为空",
                rangelength: "密码长度不能少于5位不能多于12位",
                equalTo: "两次密码不一致"
            },
            userTrueName: {
                required: "真实姓名不能为空"
            },
            userPhone: {
                required: "手机号码不能为空"
            }
        },
    });
    $("#agree").click(function() {
        if ($("input[name='agree']:checked").val()) {
            $(this).parent('label').removeClass('error');
        }
    });
});