define(['ajaxPackage',
        'app/position/recordPos',
        'app/position/trackPos',
        'jqueryConfirm'],
    function (Lprapm, RecordPos, TrackPos) {
        var contentLi = $(".content-nav .nav li");
        RecordPos.recordPos();
        contentLi.on('click', 'a', function (event) {
            event.preventDefault();
            /* Act on the event */
            var ahref = $(this).attr('href').substring(1);
            if (ahref == "recordPos") {
                /*到站记录*/
                RecordPos.recordPos();
            } else if (ahref == "trackPos") {
                /*物流订单位置追踪*/
                TrackPos.trackPos();
            } else {
                console.log("多余的li");
            }
        });

    })