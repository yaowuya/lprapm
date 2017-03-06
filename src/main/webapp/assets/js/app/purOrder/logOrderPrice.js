define(['ajaxPackage', 'timePicker', 'table', 'jqueryConfirm'],
    function (Lprapm, timePicker) {
        /**
         * 物流询价管理
         * [logOrderPrice description]
         * @return {[type]} [description]
         */
        var logOrderPrice = function () {
            var $table = $(".LOPrice-table"),
                tableColumn = [];

            /*显示下拉框*/
            $('#logOrderPrice .selectAskPur').selectpicker('show');
            // 加载日期下来框
            timePicker.picker("#createLOPTime", "#endLOPTime");

            var operateEvent = { //要放在commonrow之前，因为是赋值函数，要置前
                'click .edit': function (event, value, row, index) {
                    // console.log("edit:", row);
                    editTable(row);
                },
                'click .remove': function (event, value, row, index) {
                    // console.log("remove:", row);
                    $.confirm({
                        closeIcon: true,
                        closeIconClass: 'fa fa-close',
                        columnClass: 'small',
                        title: '撤销物流询价',
                        content: '确定要撤销物流询价吗？',
                        buttons: {
                            取消: {
                                btnClass: 'btn-default',
                            },
                            确定: {
                                btnClass: 'btn-success',
                                action: function () {
                                    Lprapm.Ajax.request({
                                        url: '/orders/revokeLOP',
                                        data: {
                                            "orderId": row.orderId,
                                            "logId": row.logId
                                        },
                                        success: function (response) {
                                            if (response.success) {
                                                $table.bootstrapTable("refresh");
                                                $.confirm({
                                                    animation: 'rotateYR',
                                                    closeAnimation: 'rotate',
                                                    backgroundDismiss: true,
                                                    content: response.messages
                                                });
                                            } else {
                                                $.dialog('撤销失败');
                                            }
                                        }
                                    });
                                }
                            },
                        }
                    });
                }
            }

            function editTable(row) {
                if (row.isAskLog == "是") {
                    $.confirm({
                        animation: 'rotateYR',
                        closeAnimation: 'rotate',
                        backgroundDismiss: true,
                        content: '已经询价,不能再次询价',
                    });
                } else {
                    Lprapm.Ajax.request({
                        url: '/orders/askOrders',
                        data: {
                            "orderId": row.orderId,
                            "isAskLog": "是"
                        },
                        success: function (response) {
                            if (response.success) {
                                $table.bootstrapTable("refresh");
                                $.confirm({
                                    animation: 'rotateYR',
                                    closeAnimation: 'rotate',
                                    backgroundDismiss: true,
                                    content: response.messages,
                                });
                            } else {
                                $.dialog(response.messages);
                            }
                        }
                    });
                }
            }

            var commonrow = {
                field: 'operate',
                title: '物流询价操作',
                width: 120,
                align: 'center',
                events: operateEvent,
                formatter: operateFormatter
            };

            function operateFormatter(value, row, index) {
                return [
                    '<a class="edit" href="javascript:void(0)" title="发起询价" style="margin-right:5px;">',
                    '<i class="glyphicon glyphicon-edit"></i>',
                    '</a> ',
                    '<a class="remove" href="javascript:void(0)" title="撤销询价">',
                    '<i class="glyphicon glyphicon-share-alt"></i>',
                    '</a>'
                ].join("");
            }

            tableColumn = [{
                field: 'orderId',
                visible: true,
                title: '采购订单id',
            }, {
                field: 'goodsName',
                visible: true,
                title: '货物名称'
            }, {
                field: 'goodsNumber',
                visible: true,
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
                field: 'isAskPur',
                visible: false,
                title: '是否采购询价'
            }, {
                field: 'isAskLog',
                visible: true,
                title: '是否物流询价'
            }, {
                field: 'purState',
                visible: false,
                title: '采购询价是否回复'
            }, {
                field: 'logState',
                visible: false,
                title: '物流询价是否回复'
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
                sortable: true, //是否启用排序
                sortOrder: 'asc', //定义排序方式 'asc' 或者 'desc'
                sortName: 'orderId', //定义排序列,通过url方式获取数据填写字段名，否则填写下标
                queryParams: function (params) { //用来向后台传请求参数,有queryParams就不用data:
                    $.extend(params, {
                        "isPur": "是",
                        "purState": "是"
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
                    row.width = 125;
                    row.class = 'title-class';
                    row.sortable = true;
                    columns.push(row);
                });
                columns.push(commonrow);
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
            $("#searchLOPBtn").click(function (e) {
                e.preventDefault();
                // console.log(searchParams());
                $table.bootstrapTable('refresh', {
                    url: '/orders/searchOrders',
                    query: searchParams()
                });
            });

            function searchParams() {
                var sendParams = $("#searchLOPForm").serializeArray();
                $.each(sendParams, function (index, val) {
                    /* iterate through array or object */
                    sendParams[val["name"]] = $.trim(val["value"]);
                });
                // console.log(sendParams);
                return sendParams;
            }
        }

        return {
            "logOrderPrice": logOrderPrice
        }
    })