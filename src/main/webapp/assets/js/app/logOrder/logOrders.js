define(['ajaxPackage',
    'app/logOrder/addLogOrder',
    'app/logOrder/manageLogOrder',
    'app/logOrder/logOrderPrice',
    'app/logOrder/signContract',
    'jqueryConfirm'],
    function(Lprapm,AddLogOrder,ManageLogOrder,LogOrderPrice,SignContract) {
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
            } else {
                console.log("多余的li");
            }
        });
        
    })