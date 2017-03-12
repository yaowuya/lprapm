/**
 * Created by zhongrf on 2017/3/11.
 */
define(['ajaxPackage',
        'app/purchase/examPurchase',
        'app/purchase/startPurchase',
        'app/purchase/checkPurchase',
        'jqueryConfirm'],
    function (Lprapm, ExamPurchase, StartPurchase, CheckPurchase) {
        var contentLi = $(".content-nav .nav li"),
            tableColumn = [];

        ExamPurchase.examPurchase();
        contentLi.on('click', 'a', function (event) {
            event.preventDefault();
            /* Act on the event */
            var ahref = $(this).attr('href').substring(1);
            if (ahref == "examPurchase") {
                // 采购审核管理
                ExamPurchase.examPurchase();
            } else if (ahref == "startPurchase") {
                // 发起采购管理
                StartPurchase.startPurchase();
            } else if (ahref == "checkPurchase") {
                // 采购完成登记管理
                CheckPurchase.checkPurchase();
            } else {
                console.log("多余的li");
            }
        });


    });