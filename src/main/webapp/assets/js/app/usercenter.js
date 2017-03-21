/**
 * Created by Administrator on 2017/1/8.
 */
define(['ajaxPackage', 'moment', 'datepicker', 'jqueryValidate', 'angular', 'table', 'jqueryConfirm'],
    function (Lprapm, moment) {
        var $addForm = $("#addUserForm");

        var picker = $('#birthDay').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: moment.locale('zh-cn'),
            dayViewHeaderFormat: 'MM YYYY',
            stepping: '2',
            showTodayButton: true,
            useCurrent: false,
        });
        picker.on('dp.change', function (e) {
            picker.data('DateTimePicker').hide();
        });

        Lprapm.Ajax.request({
            url: '/user/searchMyUser',
            success: function (response) {
                if (response.success) {
                    var jsonData = response.data[0];
                    $.each(jsonData, function (index, value) {
                        /* iterate through array or object */
                        if (index == "userSex") {
                            if (value == "man") {
                                $addForm.find("#manOption").attr('checked', 'true');
                                $addForm.find("#womanOption").removeAttr('checked');
                            }
                            if (value == "woman") {
                                $addForm.find("#manOption").removeAttr('checked');
                                $addForm.find("#womanOption").attr('checked', 'true');
                            }
                        } else {
                            $addForm.find(':input[name="' + index + '"]').val(value);
                        }
                    });
                } else {
                    $.dialog(response.messages);
                }
            }
        });
        $("#submitBtn").click(function (event) {
            /* 点击提交按钮 */
            submitData();
        });

        function submitData() {
            var formData = {};
            formData = $addForm.serializeArray();
            // console.log("formData:", formData);
            Lprapm.Ajax.request({
                url: '/user/editUser',
                data: formData,
                success: function (response) {
                    if (response.success) {

                    } else {
                        $.dialog(response.messages);
                    }
                }
            });
        }
    });
