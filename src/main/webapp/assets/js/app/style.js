define(['ajaxPackage',
        'angular',
        'jqueryValidate',
        'messageCN',
        'slimscroll'
    ],
    function(Lprapm) {
        var menu = $("#menu"),
            leftMenus = [];
        Lprapm.Ajax.request({
            url: '/menu/searchMenus',
            data: '',
            async: false,
            success: function (response) {
                if (response.success) {
                    leftMenus = response.data;
                    var iframeSrc = "";
                    // console.log(leftMenus);
                    $.each(leftMenus, function (index, val) {
                        /* iterate through array or object */
                        if (index == 0) {
                            iframeSrc = val["menuHref"];
                        }
                    });
                    var myNav = angular.module('mynav', []);
                    myNav.controller('menuCtrl', function ($scope) {
                        $scope.Menus = leftMenus;
                    });
                    myNav.controller('MainContent', function ($scope) {
                        var username = sessionStorage.getItem("User");
                        $scope.userTrueName = username;
                        $scope.IframeSrc = iframeSrc;
                    });
                    /*启动angular*/
                    angular.bootstrap(document, ['mynav']);
                }
            }
        });
        /*左边菜单栏点击颜色效果*/
        $("#menu").on('click', 'a', function (event) {
            event.preventDefault();
            // Act on the event 
            var asrc = $(this).attr('href');
            menu.find('a').removeClass('active1');
            $(this).addClass('active1');
            $("#Mainindex").attr('src', asrc);
        });

        var bodyHeight = window.screen.height;
        // var bodyHeight= window.screen.availHeight; 
        // console.log(bodyHeight);
        // $(".container-fluid").css('min-height', bodyHeight + 'px');
        // $(".content").css('height', bodyHeight+"px");
        $("#Mainindex").css('min-height', bodyHeight * 1.2 + 'px');
        // scrolling="no"
        $(".side-menu").slimScroll({
            height: bodyHeight - 200 + 'px',
            disableFadeOut: true,
        });
    });