define(['ajaxPackage',
    'app/logOrder/addLogOrder',
    'app/logOrder/manageLogOrder',
    'app/logOrder/logOrderPrice',
    'app/logOrder/signContract',
        'app/logOrder/orderTrack',
    'jqueryConfirm'],
    function (Lprapm, AddLogOrder, ManageLogOrder, LogOrderPrice, SignContract, OrderTrack) {
        var contentLi = $(".content-nav .nav li");
        AddLogOrder.addLogOrder();
        contentLi.on('click', 'a', function(event) {
            event.preventDefault();
            /* Act on the event */
            var ahref = $(this).attr('href').substring(1);
            if (ahref == "addLogOrder") {
                /*录入物流信息*/
                AddLogOrder.addLogOrder();
            } else if (ahref == "manageLogOrder") {
                /*物流信息管理*/
                ManageLogOrder.manageLogOrder();
            } else if (ahref == "logOrderPrice") {
                /*物流询价管理*/
                LogOrderPrice.logOrderPrice();
            } else if (ahref == "signContract") {
                /*签订合同管理*/
                SignContract.signContract();
            } else if (ahref == "orderTrack") {
                /*订单跟踪*/
                OrderTrack.orderTrack();
            } else {
                console.log("多余的li");
            }
        });
        
    })