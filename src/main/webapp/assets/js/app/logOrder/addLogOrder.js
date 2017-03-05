define(['ajaxPackage', 'timePicker', 'table', 'jqueryConfirm'],
    function(Lprapm, timePicker) {
        /**
         * 录入物流信息
         * [addLogOrder description]
         */
        var addLogOrder = function() {
            timePicker.picker("#addLOTime", null);
            var submitBtn = $("#addLogOrder .btn-submitLO");
            submitBtn.click(function(event) {
                event.preventDefault();
                /* Act on the event */
                var form = $(this).closest('form');
                var formData = form.serializeArray();
                console.log(formData);
                Lprapm.Ajax.request({
                    url: '/orders/insertOrders',
                    data: formData,
                    success: function(response) {
                        if (response.success) {
                            console.log("插入成功");
                            $('button.btn-resetLO').click();
                        } else {
                            $.dialog(response.messages);
                        }
                    }
                });
            });
        }
        return {
            "addLogOrder":addLogOrder
        }
    })