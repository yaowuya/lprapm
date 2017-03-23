/**
 * Created by zhongrf on 2017/3/11.
 */
define(['ajaxPackage',
        'app/logistic/replyLog',
        'app/logistic/checkLog',
        'jqueryConfirm'],
    function (Lprapm, ReplyLog, CheckLog) {
        var contentLi = $(".content-nav .nav li"),
            tableColumn = [];

        ReplyLog.replyLog();
        contentLi.on('click', 'a', function (event) {
            event.preventDefault();
            /* Act on the event */
            var ahref = $(this).attr('href').substring(1);
            if (ahref == "replyLog") {
                // 物流询价回复
                ReplyLog.replyLog();
            } else if (ahref == "checkLog") {
                // 出货登记
                CheckLog.checkLog();
            } else {
                console.log("多余的li");
            }
        });


    });