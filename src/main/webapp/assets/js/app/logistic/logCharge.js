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
                // 采购审核管理
                ReplyLog.replyLog();
            } else if (ahref == "checkLog") {
                // 发起采购管理
                CheckLog.checkLog();
            } else {
                console.log("多余的li");
            }
        });


    });