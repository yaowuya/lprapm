/**
 * Created by zhongrf on 2017/2/6.
 */
define(['ajaxPackage',
        'app/purOrder/addPurchaseOrder',
        'app/purOrder/managePurchaseOrder',
        'app/purOrder/purOrderPrice',
        'app/purOrder/logOrderPrice',
        'app/purOrder/startPur',
        'jqueryConfirm'],
    function (Lprapm, AddPurchaseOrder, ManagePurchaseOrder, PurOrderPrice, LogOrderPrice, StartPur) {
        var contentLi = $(".content-nav .nav li"),
            tableColumn = [];
        AddPurchaseOrder.addPurchaseOrder();
        contentLi.on('click', 'a', function (event) {
            event.preventDefault();
            /* Act on the event */
            var ahref = $(this).attr('href').substring(1);
            if (ahref == "addPurOrder") {
                // 录入采购信息
                AddPurchaseOrder.addPurchaseOrder();
            } else if (ahref == "managePurOrder") {
                // 采购订单管理
                ManagePurchaseOrder.managePurchaseOrder();
            } else if (ahref == "purOrderPrice") {
                // 采购询价管理
                PurOrderPrice.purOrderPrice();
            } else if (ahref == "logOrderPrice") {
                // 物流询价管理
                LogOrderPrice.logOrderPrice();
            } else if (ahref == "startPur") {
                // 发起采购管理
                StartPur.startPur();
            } else {
                console.log("多余的li");
            }
        });


    });