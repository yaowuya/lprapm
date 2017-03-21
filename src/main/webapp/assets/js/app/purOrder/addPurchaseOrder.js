define(['ajaxPackage', 'timePicker', 'select', 'table', 'jqueryConfirm'],
    function (Lprapm, timePicker, Select) {
        /**
         * 录入采购订单
         * [addPurchaseOrder description]
         */
        var addPurchaseOrder = function () {
            var submitBtn = $("#addPurOrder .btn-submitPO"),
                $province = $("#orderProvince"),
                $city = $("#orderCity"),
                $area = $("#orderArea"),
                $receiptProvince = $("#receiptProvince"),
                $receiptCity = $("#receiptCity"),
                $receiptArea = $("#receiptArea"),
                selectOption = {
                    isSearch: true, //是否显示搜索框
                    multiple: false, //是否多选
                    width: '100%', //长度
                    actionBox: false, //是否展示全选、取消按钮
                    // title: '请选择你的...', //默认提示
                    dataSize: 7, //最多显示个数，数据多时会有滚动条
                };
            Select.selectList.option($province, selectOption, '/address/province', "province", "provinceid", []);
            $city.selectpicker('show');
            $area.selectpicker('show');

            Select.selectList.option($receiptProvince, selectOption, '/address/province', "province", "provinceid", []);
            $receiptCity.selectpicker('show');
            $receiptArea.selectpicker('show');

            $province.on('change', function (event) {
                event.preventDefault();
                /* Act on the event */
                var provinceVal = $(this).val();
                Select.selectList.option($city, selectOption, "/address/city", "city", "cityid", [], {
                    "provinceid": provinceVal
                });
            });

            $receiptProvince.on('change', function (event) {
                event.preventDefault();
                /* Act on the event */
                var provinceVal = $(this).val();
                Select.selectList.option($receiptCity, selectOption, "/address/city", "city", "cityid", [], {
                    "provinceid": provinceVal
                });
            });

            $city.on('change', function (event) {
                event.preventDefault();
                /* Act on the event */
                if ($province.val() == null || $province.val() == "") {
                    $city.selectpicker('destroy');
                } else {
                    var cityVal = $(this).val();
                    Select.selectList.option($area, selectOption, "/address/area", "area", "areaid", [], {
                        "cityid": cityVal
                    });
                }
            });

            $receiptCity.on('change', function (event) {
                event.preventDefault();
                /* Act on the event */
                if ($receiptProvince.val() == null || $receiptProvince.val() == "") {
                    $receiptCity.selectpicker('destroy');
                } else {
                    var cityVal = $(this).val();
                    Select.selectList.option($receiptArea, selectOption, "/address/area", "area", "areaid", [], {
                        "cityid": cityVal
                    });
                }
            });

            $area.on('change', function (event) {
                event.preventDefault();
                /* Act on the event */
                if ($city.val() == null || $city.val() == "") {
                    $area.selectpicker('destroy');
                }
            });

            $receiptArea.on('change', function (event) {
                event.preventDefault();
                /* Act on the event */
                if ($receiptCity.val() == null || $receiptCity.val() == "") {
                    $receiptArea.selectpicker('destroy');
                }
            });


            timePicker.picker("#addPOTime", null);

            submitBtn.click(function (event) {
                event.preventDefault;
                event.stopPropagation;
                var p = $province.val() == null ? false : true,
                    c = $city.val() == null ? false : true,
                    a = $area.val() == null ? false : true;
                rp = $receiptProvince.val() == null ? false : true,
                    rc = $receiptCity.val() == null ? false : true,
                    ra = $receiptArea.val() == null ? false : true;

                if (p && c && a && rp && rc && ra) {
                    /* Act on the event */
                    var form = $(this).closest('form');
                    var formData = form.serializeArray();
                    // console.log(formData);
                    Lprapm.Ajax.request({
                        url: '/orders/insertOrders',
                        data: formData,
                        success: function (response) {
                            if (response.success) {
                                console.log("插入成功");
                                $('button.btn-resetPO').click();
                            } else {
                                $.dialog(response.messages);
                            }
                        }
                    });
                } else {
                    $.dialog("请填好地址");
                }

            });

            $('button.btn-resetPO').on('click', function (event) {
                event.preventDefault();
                /* Act on the event */
                Select.selectList.select($province, []);
                Select.selectList.select($city, []);
                Select.selectList.select($area, []);
                Select.selectList.select($receiptProvince, []);
                Select.selectList.select($receiptCity, []);
                Select.selectList.select($receiptArea, []);
                window.location.reload();
                // $("#addPurOrder form").find(':input').val("");
            });
        }

        return {
            "addPurchaseOrder": addPurchaseOrder
        }
    })
