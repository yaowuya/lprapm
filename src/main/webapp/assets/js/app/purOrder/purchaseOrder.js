/**
 * Created by zhongrf on 2017/2/6.
 */
define(['ajaxPackage',
        'app/purOrder/addPurchaseOrder',
        'app/purOrder/managePurchaseOrder',
        'app/purOrder/purOrderPrice',
        'app/purOrder/logOrderPrice',
        'jqueryConfirm'],
    function (Lprapm, AddPurchaseOrder, ManagePurchaseOrder, PurOrderPrice, LogOrderPrice) {
        var contentLi = $(".content-nav .nav li"),
            tableColumn = [];

        AddPurchaseOrder.addPurchaseOrder();
        contentLi.on('click', 'a', function (event) {
            event.preventDefault();
            /* Act on the event */
            var ahref = $(this).attr('href').substring(1);
            if (ahref == "addPurOrder") {
                AddPurchaseOrder.addPurchaseOrder();
            } else if (ahref == "managePurOrder") {
                ManagePurchaseOrder.managePurchaseOrder();
            } else if (ahref == "purOrderPrice") {
                PurOrderPrice.purOrderPrice();
            } else if (ahref == "logOrderPrice") {
                LogOrderPrice.logOrderPrice();
            } else {
                console.log("多余的li");
            }
        });


    });