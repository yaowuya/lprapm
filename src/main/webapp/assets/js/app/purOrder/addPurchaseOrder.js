define(['ajaxPackage', 'timePicker', 'select', 'table', 'jqueryConfirm'],
    function (Lprapm, timePicker, Select) {
        /**
         * 录入采购订单
         * [addPurchaseOrder description]
         */
        var addPurchaseOrder = function () {
            var submitBtn = $("#addPurOrder .btn-submitPO"),
                $province = $(".selectProvince"),
                $city = $(".selectCity"),
                $area = $(".selectArea"),
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

            $province.on('change', function (event) {
                event.preventDefault();
                /* Act on the event */
                var provinceVal = $(this).val();
                Select.selectList.option($city, selectOption, "/address/city", "city", "cityid", [], {
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

            $area.on('change', function (event) {
                event.preventDefault();
                /* Act on the event */
                if ($city.val() == null || $city.val() == "") {
                    $area.selectpicker('destroy');
                }
            });
            timePicker.picker("#addPOTime", null);

            submitBtn.click(function (event) {
                event.stopPropagation;
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
            });
        }

        return {
            "addPurchaseOrder": addPurchaseOrder
        }
    })