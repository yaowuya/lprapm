define(['ajaxPackage', 'timePicker', 'table', 'jqueryConfirm'],
    function(Lprapm, timePicker) {
        var manageLogOrder = function() {
            var $table = $(".MLO-table"),
                $modal = $("#myModal"),
                $addForm = $("#addMLOForm"),
                tableColumn = [];

            timePicker.picker("#createMLOTime", "#endMLOTime");
            var operateEvent = { //要放在commonrow之前，因为是赋值函数，要置前
                'click .edit': function(event, value, row, index) {
                    // console.log("edit:", row);
                    editTable(row);
                },
                'click .remove': function(event, value, row, index) {
                    // console.log("remove:", row);
                    $.confirm({
                        closeIcon: true,
                        closeIconClass: 'fa fa-close',
                        columnClass: 'small',
                        title: '删除',
                        content: '确定要删除这条记录吗？',
                        buttons: {
                            取消: {
                                btnClass: 'btn-default',
                            },
                            确定: {
                                btnClass: 'btn-success',
                                action: function() {
                                    Lprapm.Ajax.request({
                                        url: '/orders/deleteOrders',
                                        data: {
                                            "orderId": row.orderId,
                                            "oeId": row.oeId,
                                            "goodsId": row.goodsId,
                                            "receiptId": row.receiptId,
                                            "purId": row.purId,
                                            "logId": row.logId
                                        },
                                        success: function(response) {
                                            if (response.success) {
                                                $table.bootstrapTable("refresh");
                                            } else {
                                                $.dialog('删除失败');
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
                timePicker.picker("#editMLOTime", null);
                if (row.isAskLog == "是" || row.isSure == "是") {
                    $.dialog('已经询价或者签订合同,不能修改');
                } else {
                    $("#myModalLabel").text("修改");
                    showModal();
                    $.each(row, function(index, value) {
                        /* iterate through array or object */
                        $addForm.find(":input[name=" + index + "]").val(value);
                    });
                }
            }
            var commonrow = {
                field: 'operate',
                title: '操作',
                width: 100,
                align: 'center',
                events: operateEvent,
                formatter: operateFormatter
            };

            function operateFormatter(value, row, index) {
                return [
                    '<a class="edit" href="javascript:void(0)" title="编辑">',
                    '<i class="glyphicon glyphicon-edit"></i>',
                    '</a> ',
                    '<a class="remove" href="javascript:void(0)" title="删除">',
                    '<i class="glyphicon glyphicon-remove"></i>',
                    '</a>'
                ].join("");
            }

            tableColumn = [{
                field: 'orderId',
                visible: true,
                title: '物流订单编号'
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
                visible: true,
                title: '货物体积'
            }, {
                field: 'goodsPerweight',
                visible: true,
                title: '货物单位重量'
            }, {
                field: 'createTime',
                visible: true,
                title: '订单创建日期'
            }, {
                field: 'endTime',
                visible: true,
                title: '期望送达日期'
            }, {
                field: 'province',
                visible: false,
                title: '订单省份'
            }, {
                field: 'city',
                visible: false,
                title: '订单地级市'
            }, {
                field: 'area',
                visible: false,
                title: '订单县级市'
            }, {
                field: 'province',
                visible: false,
                title: '订单省份'
            }, {
                field: 'city',
                visible: false,
                title: '订单地级市'
            }, {
                field: 'area',
                visible: false,
                title: '订单县级市'
            }, {
                field: 'orderAddress',
                visible: false,
                title: '订单街道地址'
            }, {
                field: 'userName',
                visible: false,
                title: '用户名称'
            }, {
                field: 'receiptName',
                visible: true,
                title: '收货人名称'
            }, {
                field: 'receiptPhone',
                visible: true,
                title: '收货人电话'
            }, {
                field: 'receiptProvince',
                visible: false,
                title: '收货人省份'
            }, {
                field: 'receiptCity',
                visible: false,
                title: '收货人地级市'
            }, {
                field: 'receiptArea',
                visible: false,
                title: '收货人县级市'
            }, {
                field: 'receiptAddress',
                visible: false,
                title: '收货人街道地址'
            }, {
                field: 'isPur',
                visible: true,
                title: '是否是采购订单'
            }, {
                field: 'receiptState',
                visible: true,
                title: '是否确认收货'
            }, {
                field: 'isAskPur',
                visible: false,
                title: '是否采购询价'
            }, {
                field: 'isAskLog',
                visible: true,
                title: '是否物流询价'
            }, {
                field: 'isSure',
                visible: true,
                title: '是否签订合同'
            }, {
                field: 'oeId',
                visible: false,
                title: 'oeId'
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
                sortOrder: 'asc', //定义排序方式 'asc' 或者 'desc'
                sortName: 'orderId', //定义排序列,通过url方式获取数据填写字段名，否则填写下标
                queryParams: function(params) { //用来向后台传请求参数,有queryParams就不用data:
                    $.extend(params, {
                        "isPur": "否"
                    }); //searchParams返回的是参数格式  return {N_id:abc}
                    return params;
                },
                url: '/orders/searchOrders', //请求接口
                columns: getColumns(tableColumn), //列数据,也可以通过函数来获取
                detailView: true, //详细查看按钮
                detailFormatter: detailFormatter, //显示详细查看数据
                onDblClickRow: function(row, $element, field) {
                    editTable(row);
                },
                onLoadSuccess: function(response) { //请求成功，返回数据
                    // 数据操作
                    // 自动显示过长的数据
                    // console.log(response.data);
                    var arrow = $(".title-class");
                    $.each(arrow, function(index, val) {
                        /* iterate through array or object */
                        $(this).attr("title", val.innerText);
                    });
                }
            });

            function getColumns(params) {
                var columns = [];
                // columns.push(state);
                $.each(params, function(index, val) {
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
            $("#searchMLOBtn").click(function(e) {
                e.preventDefault();
                // console.log(searchParams());
                $table.bootstrapTable('refresh', {
                    url: '/orders/searchOrders',
                    query: searchParams()
                });
            });

            function searchParams() {
                var sendParams = $("#searchMLOForm").serializeArray();
                $.each(sendParams, function(index, val) {
                    /* iterate through array or object */
                    sendParams[val["name"]] = $.trim(val["value"]);
                });
                // console.log(sendParams);
                return sendParams;
            }

            function showModal() {
                $modal.modal("show");
            }

            /*关闭重置表单*/
            $modal.on('hide.bs.modal', function() {
                $("#resetBtn").click();
            });
            $("#submitBtn").click(function(event) {
                /* 点击提交按钮 */
                submitData();
            });

            function submitData() {
                var formData = {};
                formData = $addForm.serializeArray();
                Lprapm.Ajax.request({
                    url: '/orders/updateOrders',
                    data: formData,
                    success: function(response) {
                        if (response.success) {
                            $table.bootstrapTable("refresh");
                            $modal.modal('hide');
                        } else {
                            $.dialog(response.messages);
                        }
                    }
                });
            }
        }

        return {
            "manageLogOrder": manageLogOrder
        }
    })
