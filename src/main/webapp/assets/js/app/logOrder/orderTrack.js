define(['ajaxPackage', 'timePicker', 'select',
        'table', 'jqueryConfirm'
    ],
    function (Lprapm, timePicker, Select) {
        var orderTrack = function () {
            var $table = $(".TP-table"),
                tableColumn = [],
                modalColumn = [],
                $modalTable = $(".OT-table"),
                $modal = $("#OTModal"),
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

            modalColumn = [{
                field: 'positionId',
                visible: true,
                title: '物流编号',
            }, {
                field: 'carplanId',
                visible: false,
                title: '配车计划Id'
            }, {
                field: 'orderId',
                visible: false,
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
                title: '省份'
            }, {
                field: 'city',
                visible: true,
                title: '地级市'
            }, {
                field: 'area',
                visible: true,
                title: '县级市'
            }, {
                field: 'trackStatus',
                visible: true,
                title: '位置状态'
            }, {
                field: 'trackTime',
                visible: true,
                title: '时间'
            }];
            var operateEvent = { //要放在commonrow之前，因为是赋值函数，要置前
                'click .edit': function (event, value, row, index) {
                    // console.log("edit:", row);
                    $modal.modal("show");
                    $modalTable.bootstrapTable({
                        singleSelect: false, //设置True 将禁止多选
                        striped: true, //设置隔行变色
                        clickToSelect: true, //设置true 将在点击行时，自动选择rediobox 和 checkbox
                        classes: "table table-no-bordered",
                        rowStyle: rowStyle,
                        sortable: true, //是否启用排序
                        sortOrder: 'desc', //定义排序方式 'asc' 或者 'desc'
                        sortName: 'trackTime', //定义排序列,通过url方式获取数据填写字段名，否则填写下标
                        queryParams: function (params) { //用来向后台传请求参数,有queryParams就不用data:
                            $.extend(params, {
                                "orderId": row.orderId,
                                "userId": row.userId
                            }); //searchParams返回的是参数格式  return {N_id:abc}
                            return params;
                        },
                        url: '/orders/queryTrack', //请求接口
                        columns: getModalColumns(modalColumn),
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

                    function getModalColumns(params) {
                        var columns = [];
                        // columns.push(state);
                        $.each(params, function (index, val) {
                            /* iterate through array or object */
                            var row = {};
                            row.field = val.field;
                            row.visible = val.visible;
                            row.title = val.title;
                            // row.width = 150;
                            row.class = 'title-class';
                            row.sortable = true;
                            columns.push(row);
                        });
                        return columns;
                    }
                }
            }
            $modal.on('shown.bs.modal', function () {
                $modalTable.bootstrapTable('resetView');
            });
            /*关闭重置表单*/
            $modal.on('hide.bs.modal', function () {
                $modalTable.bootstrapTable('destroy');
            });
            var commonrow = {
                field: 'operate',
                title: '物流详情',
                width: 120,
                align: 'center',
                events: operateEvent,
                formatter: operateFormatter
            };

            function operateFormatter(value, row, index) {
                return [
                    '<a class="edit" href="javascript:void(0)" title="物流详情" style="color:red;margin-right:5px;">',
                    '物流详情',
                    '</a> '
                ].join("");
            }

            tableColumn = [{
                field: 'orderId',
                visible: true,
                title: '物流订单编号',
            }, {
                field: 'goodsName',
                visible: true,
                title: '货物名称'
            }, {
                field: 'goodsNumber',
                visible: false,
                title: '货物数量'
            }, {
                field: 'goodsVolume',
                visible: false,
                title: '货物体积'
            }, {
                field: 'goodsPerweight',
                visible: false,
                title: '货物单位重量'
            }, {
                field: 'createTime',
                visible: true,
                title: '订单创建日期'
            }, {
                field: 'endTime',
                visible: false,
                title: '期望送达日期'
            }, {
                field: 'orderAddress',
                visible: false,
                title: '订单地址'
            }, {
                field: 'receiptName',
                visible: true,
                title: '收货人名称'
            }, {
                field: 'receiptPhone',
                visible: false,
                title: '收货人电话'
            }, {
                field: 'receiptAddress',
                visible: false,
                title: '收货地址'
            }, {
                field: 'isSure',
                visible: false,
                title: '是否签订合同'
            }, {
                field: 'isAskLog',
                visible: false,
                title: '是否物流询价'
            }, {
                field: 'oeState',
                visible: false,
                title: '审核状态'
            }, {
                field: 'oeDept',
                visible: false,
                title: '审核部门'
            }, {
                field: 'oePerson',
                visible: false,
                title: '审核人'
            }, {
                field: 'oeReason',
                visible: false,
                title: '审核原因'
            }, {
                field: 'logState',
                visible: true,
                title: '物流状态'
            }, {
                field: 'logDept',
                visible: false,
                title: '审核部门'
            }, {
                field: 'logPerson',
                visible: false,
                title: '审核人'
            }, {
                field: 'logPrice',
                visible: true,
                title: '物流报价'
            }, {
                field: 'goodsId',
                visible: false,
                title: 'goodsId'
            }, {
                field: 'receiptId',
                visible: false,
                title: 'receiptId'
            }, {
                field: 'userId',
                visible: false,
                title: 'userId'
            }, {
                field: 'purId',
                visible: false,
                title: 'purId'
            }, {
                field: 'logId',
                visible: false,
                title: 'logId'
            }];
            /*表格加载*/
            $table.bootstrapTable({
                singleSelect: false, //设置True 将禁止多选
                striped: true, //设置隔行变色
                clickToSelect: true, //设置true 将在点击行时，自动选择rediobox 和 checkbox
                classes: "table table-no-bordered",
                rowStyle: rowStyle,
                sortable: true, //是否启用排序
                sortOrder: 'desc', //定义排序方式 'asc' 或者 'desc'
                sortName: 'createTime', //定义排序列,通过url方式获取数据填写字段名，否则填写下标
                queryParams: function (params) { //用来向后台传请求参数,有queryParams就不用data:
                    $.extend(params, {
                        "logState": "已出发"
                    }); //searchParams返回的是参数格式  return {N_id:abc}
                    return params;
                },
                url: '/orders/searchOrders', //请求接口
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
                columns.push(commonrow);
                return columns;
            }

            function rowStyle(row, index) {
                var classes = ['active', 'success', 'info', 'warning', 'danger'];
                if (index % 2 === 0 && index / 2 < classes.length) {
                    return {
                        classes: classes[index / 2]
                    };
                }
                return {};
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
                    url: '/orders/searchOrders',
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
