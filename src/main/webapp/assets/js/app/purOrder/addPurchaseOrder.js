define(['ajaxPackage', 'timePicker', 'table', 'jqueryConfirm'],
    function (Lprapm, timePicker) {
        /**
         * 录入采购订单
         * [addPurchaseOrder description]
         */
        var addPurchaseOrder = function () {
            var submitBtn = $("#addPurOrder .btn-submitPO");
            timePicker.picker("#addPOTime", null);
            submitBtn.click(function (event) {
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