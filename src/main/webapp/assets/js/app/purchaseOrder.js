/**
 * Created by zhongrf on 2017/2/6.
 */
define(['ajaxPackage', 'moment', 'select', 'datepicker', 'jqueryValidate', 'angular', 'table', 'jqueryConfirm'],
    function(Lprapm, moment, selectPackage) {
        var contentLi = $(".content-nav .nav li"),
            tableColumn = [];

        addPurchaseOrder();
        contentLi.on('click', 'a', function(event) {
            event.preventDefault();
            /* Act on the event */
            var ahref = $(this).attr('href').substring(1);
            if (ahref == "addPurOrder") {
                addPurchaseOrder();
            } else if (ahref == "managePurOrder") {
                managePurchaseOrder();
            } else if (ahref == "purOrderPrice") {
                purOrderPrice();
            } else if (ahref == "logOrderPrice") {

            } else if (ahref == "signContract") {

            } else if (ahref == "orderTrack") {

            } else {
                console.log("多余的li");
            }
        });
        /**
         * 加载日期函数
         * [timePicker description]
         * @param  {[type]} time1 [description]
         * @param  {[type]} time2 [description]
         * @return {[type]}       [description]
         */
        function timePicker(time1, time2) {
            if (time1 != null) {
                if (time2 != null) {
                    var picker1 = $(time1).datetimepicker({
                        format: 'YYYY-MM-DD',
                        locale: moment.locale('zh-cn'),
                        dayViewHeaderFormat: 'YYYY MM',
                        stepping: '2',
                        showTodayButton: true,
                        useCurrent: false,
                    });
                    var picker2 = $(time2).datetimepicker({
                        format: 'YYYY-MM-DD',
                        locale: moment.locale('zh-cn'),
                        dayViewHeaderFormat: 'YYYY MM',
                        stepping: '2',
                        showTodayButton: true,
                        useCurrent: false,
                    });
                    //动态设置最小值  
                    picker1.on('dp.change', function(e) {
                        picker2.data('DateTimePicker').minDate(e.date);
                    });
                    //动态设置最大值  
                    picker2.on('dp.change', function(e) {
                        picker1.data('DateTimePicker').maxDate(e.date);
                    });
                } else {
                    var picker = $(time1).datetimepicker({
                        format: 'YYYY-MM-DD',
                        locale: moment.locale('zh-cn'),
                        dayViewHeaderFormat: 'YYYY MM',
                        stepping: '2',
                        showTodayButton: true,
                        useCurrent: false,
                    });
                    picker.on('dp.change', function(e) {
                        picker.data('DateTimePicker').hide();
                    });
                }
            } else {
                console.log("第一个参数不能为空");
            }
        }

        /**
         * 录入采购订单
         * [addPurchaseOrder description]
         */
        function addPurchaseOrder() {
                var submitBtn = $("#addPurOrder .btn-submitPO");
                timePicker("#addPOTime", null);
                submitBtn.click(function(event) {
                    /* Act on the event */
                    var form = $(this).closest('form');
                    var formData = form.serializeArray();
                    console.log(formData);
                    Lprapm.Ajax.request({
                        url: '/purchaseOrder/insertPO',
                        data: formData,
                        success: function(response) {
                            if (response.success) {
                                console.log("插入成功");
                                $('button.btn-resetPO').click();
                            } else {
                                $.dialog(response.messages);
                            }
                        }
                    });
                });
            }
            /**
             * 采购订单管理
             * @return {[type]} [description]
             */
        function managePurchaseOrder() {
                var $table = $(".MPO-table"),
                    $modal = $("#myModal"),
                    $addForm = $("#addMPOForm"),
                    tableColumn = [];
                timePicker("#createMPOTime", "#endMPOTime");
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
                                            url: '/purchaseOrder/deletePO',
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
                    timePicker("#editMPOTime", null);
                    if (row.isAskPur == "是" || row.isAskLog == "是" || row.isSure == "是") {
                        $.dialog('已经询价或者签订合同,不能修改');
                    } else {
                        $("#myModalLabel").text("修改");
                        showModal();
                        $.each(row, function(index, value) {
                            /* iterate through array or object */
                            $addForm.find("input[name=" + index + "]").val(value);
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
                    title: '采购订单id'
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
                    field: 'orderAddress',
                    visible: true,
                    title: '订单地址'
                }, {
                    field: 'receiptName',
                    visible: true,
                    title: '收货人名称'
                }, {
                    field: 'receiptPhone',
                    visible: true,
                    title: '收货人电话'
                }, {
                    field: 'receiptAddress',
                    visible: true,
                    title: '收货地址'
                }, {
                    field: 'receiptState',
                    visible: true,
                    title: '是否确认收货'
                }, {
                    field: 'isPur',
                    visible: true,
                    title: '是否是采购订单'
                }, {
                    field: 'isAskPur',
                    visible: true,
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
                    sortable: true, //是否启用排序
                    sortOrder: 'asc', //定义排序方式 'asc' 或者 'desc'
                    sortName: 'orderId', //定义排序列,通过url方式获取数据填写字段名，否则填写下标
                    url: '/purchaseOrder/searchPO', //请求接口
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

                function detailFormatter(index, row) {
                        var html = [];
                        $.each(tableColumn, function(index, val) {
                            /* iterate through array or object */
                            if (val["visible"] == true) {
                                $.each(row, function(key, value) {
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
                $("#searchMPOBtn").click(function(e) {
                    e.preventDefault();
                    // console.log(searchParams());
                    $table.bootstrapTable('refresh', {
                        url: '/purchaseOrder/searchPO',
                        query: searchParams()
                    });
                });

                function searchParams() {
                    var sendParams = $("#searchMPOForm").serializeArray();
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
                        url: '/purchaseOrder/updatePO',
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
            /**
             * 采购询价管理
             * @return {[type]} [description]
             */
        function purOrderPrice() {
            var $table = $(".POPrice-table"),
                tableColumn = [];

            /*显示下拉框*/
            $('#purOrderPrice .selectAskPur').selectpicker('show');
            // 加载日期下来框
            timePicker("#createPOPTime", "#endPOPTime");

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
                        title: '撤销询价',
                        content: '确定要撤销询价吗？',
                        buttons: {
                            取消: {
                                btnClass: 'btn-default',
                            },
                            确定: {
                                btnClass: 'btn-success',
                                action: function() {
                                    Lprapm.Ajax.request({
                                        url: '/purchaseOrder/revokePOP',
                                        data: {
                                            "orderId": row.orderId,
                                            "purId":row.purId
                                        },
                                        success: function(response) {
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
                timePicker("#editMPOTime", null);
                if (row.isAskPur == "是") {
                    $.confirm({
                        animation: 'rotateYR',
                        closeAnimation: 'rotate',
                        backgroundDismiss: true,
                        content: '已经询价,不能再次询价',
                    });
                } else {
                    Lprapm.Ajax.request({
                        url: '/purchaseOrder/askPOP',
                        data: {"orderId":row.orderId,"isAskPur":"是"},
                        success: function(response) {
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
                title: '采购询价操作',
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
                visible: true,
                title: '是否采购询价'
            }, {
                field: 'purState',
                visible: true,
                title: '询价是否回复'
            }, {
                field: 'purDept',
                visible: false,
                title: '审核部门'
            }, {
                field: 'purPerson',
                visible: false,
                title: '审核人'
            }, {
                field: 'purPrice',
                visible: true,
                title: '采购报价'
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
            }];
            /*表格加载*/
            $table.bootstrapTable({
                singleSelect: false, //设置True 将禁止多选
                striped: true, //设置隔行变色
                clickToSelect: true, //设置true 将在点击行时，自动选择rediobox 和 checkbox
                sortable: true, //是否启用排序
                sortOrder: 'asc', //定义排序方式 'asc' 或者 'desc'
                sortName: 'orderId', //定义排序列,通过url方式获取数据填写字段名，否则填写下标
                url: '/purchaseOrder/searchPOP', //请求接口
                columns: getColumns(tableColumn), //列数据,也可以通过函数来获取
                detailView: true, //详细查看按钮
                detailFormatter: detailFormatter, //显示详细查看数据
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
                    $.each(tableColumn, function(index, val) {
                        /* iterate through array or object */
                        if (val["field"].indexOf('Id')<0) {
                            $.each(row, function(key, value) {
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
            $("#searchPOPBtn").click(function(e) {
                e.preventDefault();
                // console.log(searchParams());
                $table.bootstrapTable('refresh', {
                    url: '/purchaseOrder/searchPOP',
                    query: searchParams()
                });
            });

            function searchParams() {
                var sendParams = $("#searchPOPForm").serializeArray();
                $.each(sendParams, function(index, val) {
                    /* iterate through array or object */
                    sendParams[val["name"]] = $.trim(val["value"]);
                });
                // console.log(sendParams);
                return sendParams;
            }
        }
    });