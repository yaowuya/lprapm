define(['ajaxPackage',
        'app/transport/carPlan',
        'app/transport/outCar',
        'jqueryConfirm'],
    function (Lprapm, CarPlan, OutCar) {
        var contentLi = $(".content-nav .nav li");
        CarPlan.carPlan();
        contentLi.on('click', 'a', function (event) {
            event.preventDefault();
            /* Act on the event */
            var ahref = $(this).attr('href').substring(1);
            if (ahref == "carPlan") {
                /*配车计划*/
                CarPlan.carPlan();
            } else if (ahref == "outCar") {
                /*出车管理*/
                OutCar.outCar();
            } else {
                console.log("多余的li");
            }
        });

    })