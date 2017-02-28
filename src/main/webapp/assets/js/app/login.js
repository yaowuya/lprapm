define(['ajaxPackage', 'messageCN'], function(ajaxPackage) {
    var userData={};
    // 验证码
    $("#verifycode").click(function(event) {
        /* Act on the event */
        $("#verifycode").attr('src', '/user/verifycode?r=' + new Date().getTime());
    });
    // 登录验证
    jQuery.validator.addMethod("chekcode", function(value, element) {
        var flag = false;
        ajaxPackage.Ajax.request({
            url: '/user/checkVerify',
            data: {
                'checkCode': value
            },
            async: false,
            success: function(response) {
                flag = response.success;
            }
        });
        return flag;
    }, "验证码错误");
    $("#loginForm").validate({
        submitHandler: function(form) {
            var params = $("#loginForm").serializeArray();
            // console.log(params);
            ajaxPackage.Ajax.request({
                url: '/user/login',
                type: 'post',
                data: params,
                success: function(response) {
                    if (response.success) {
                        sessionStorage.clear();
                        userData=response.data;
                        sessionStorage.setItem("User",userData.userTrueName);
                        window.location.href = "/redirect/index";
                    } else {
                        var loginResponse = response.data.User;
                        console.log(loginResponse);
                        $("#userEmail").val(loginResponse.userEmail);
                        $("userPassword").val(loginResponse.userPassword);
                        if($.trim(response.messages)==$.trim("loginError")){
                            $(".responseError").text(response.data.msg);
                        }else{
                            $(".responseError").text(response.messages);
                        }
                        $(".responseError").css('display', 'block');
                        var focusInput = $("#loginForm").find('input');
                        $.each(focusInput, function(index, val) {
                            /* iterate through array or object */
                            $(this).focus(function(event) {
                                /* Act on the event */
                                $(".responseError").css('display', 'none');
                            });
                        });
                    }
                }
            });
        },
        errorPlacement: function(error, element) {
            $(element).after(error);
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
            userCheckCode: {
                required: true,
                chekcode: true
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
            userCheckCode: {
                required: "请输入验证码"
            }
        }
    });
});