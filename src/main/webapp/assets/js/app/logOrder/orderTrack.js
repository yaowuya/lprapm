define(['ajaxPackage', 'timePicker', 'select',
        'table', 'jqueryConfirm'
    ],
    function (Lprapm, timePicker, Select) {
        var orderTrack = function () {
            var $table = $(".TP-table"),
                tableColumn = [],
                $orderId = $("#orderId"),
                selectOption = {
                    isSearch: true, //是否显示搜索框
                    multiple: false, //是否多选
                    width: '100%', //长度
                    actionBox: true, //是否展示全选、取消按钮
                    title: '请选择...', //默认提示
                    dataSize: 5, //最多显示个数，数据多时会有滚动条
                };
            var username = sessionStorage.getItem("User");
            timePicker.picker("#createTPTime", "#endTPTime");
            Select.selectList.option($orderId, selectOption, "/orders/queryOrders", "userName", "orderId", [], {"userName": username});

            tableColumn = [{
                field: 'positionId',
                visible: true,
                title: '跟踪编号',
            }, {
                field: 'carplanId',
                visible: false,
                title: '配车计划Id'
            }, {
                field: 'orderId',
                visible: true,
                title: '物流订单Id'
            }, {
                field: 'userName',
                visible: true,
                title: '用户名'
            }, {
                field: 'goodsName',
                visible: true,
                title: '货物名称'
            }, {
                field: 'provinceid',
                visible: false,
                title: '省份id'
            }, {
                field: 'cityid',
                visible: false,
                title: '地级市id'
            }, {
                field: 'areaid',
                visible: false,
                title: '县级市id'
            }, {
                field: 'province',
                visible: true,
                title: '站点省份'
            }, {
                field: 'city',
                visible: true,
                title: '站点地级市'
            }, {
                field: 'area',
                visible: true,
                title: '站点县级市'
            }, {
                field: 'trackStatus',
                visible: true,
                title: '位置状态'
            }, {
                field: 'trackTime',
                visible: true,
                title: '创建时间'
            }];

            /*表格加载*/
            $table.bootstrapTable({
                singleSelect: false, //设置True 将禁止多选
                striped: true, //设置隔行变色
                clickToSelect: true, //设置true 将在点击行时，自动选择rediobox 和 checkbox
                sortable: true, //是否启用排序
                sortOrder: 'desc', //定义排序方式 'asc' 或者 'desc'
                sortName: 'trackTime', //定义排序列,通过url方式获取数据填写字段名，否则填写下标
                queryParams: function (params) { //用来向后台传请求参数,有queryParams就不用data:
                    $.extend(params, {
                        "userName": username
                    }); //searchParams返回的是参数格式  return {N_id:abc}
                    return params;
                },
                url: '/orders/queryTrack', //请求接口
                columns: getColumns(tableColumn), //列数据,也可以通过函数来获取
                detailView: true, //详细查看按钮
                detailFormatter: detailFormatter, //显示详细查看数据
                onLoadSuccess: function (response) { //请求成功，返回数据
                    // 数据操作
                    // 自动显示过长的数据
                    // console.log(response.data);
                    var arrow = $(".title-class");
                    $.each(arrow, function (index, val) {
                        /* iterate through array or object */
                        $(this).attr("title", val.innerText);
                    });
                }
            });

            function getColumns(params) {
                var columns = [];
                // columns.push(state);
                $.each(params, function (index, val) {
                    /* iterate through array or object */
                    var row = {};
                    row.field = val.field;
                    row.visible = val.visible;
                    row.title = val.title;
                    row.width = 150;
                    row.class = 'title-class';
                    row.sortable = true;
                    columns.push(row);
                });
                // columns.push(commonrow);
                return columns;
            }

            function detailFormatter(index, row) {
                var html = [];
                $.each(tableColumn, function (index, val) {
                    /* iterate through array or object */
                    if (val["field"].indexOf('Id') < 0) {
                        $.each(row, function (key, value) {
                            /* iterate through array or object */
                            if (val["field"] == key) {
                                html.push('<p><b>' + val["title"] + ':</b> ' + value + '</p>');
                            }
                        });
                    }
                });
                return html.join('');
            }

            /*点击查询按钮*/
            $("#searchTPBtn").click(function (e) {
                e.preventDefault();
                // console.log(searchParams());
                $table.bootstrapTable('refresh', {
                    url: '/orders/queryTrack',
                    query: searchParams()
                });
            });

            function searchParams() {
                var sendParams = {},
                    selectData = $("#searchTPForm").find(':input');
                $.each(selectData, function (index, val) {
                    /* iterate through array or object */
                    sendParams[$(this).attr("name")] = $.trim($(this).val());
                });
                return sendParams;
            }

        }

        return {
            "orderTrack": orderTrack
        }
    })
