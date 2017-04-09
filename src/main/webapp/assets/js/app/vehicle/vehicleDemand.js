define(['ajaxPackage', 'select', 'table', 'jqueryConfirm'],
    function (Lprapm, Select) {

        var $table = $(".table"),
            $carTable = $(".car-table"),
            $modal = $("#myModal"),
            $addForm = $("#addForm"),
            tableColumn = [],
            carTableColumn = [],
            selectOption = {
                isSearch: true, //是否显示搜索框
                multiple: false, //是否多选
                width: '95%', //长度
                actionBox: true, //是否展示全选、取消按钮
                title: '请选择你的...', //默认提示
                dataSize: 5, //最多显示个数，数据多时会有滚动条
            };

        $('.selectAskPur').selectpicker('show');
        // timePicker.picker("#createTime", "#endTime");
        var username = sessionStorage.getItem("User");
        var operateEvent = { //要放在commonrow之前，因为是赋值函数，要置前
            'click .edit': function (event, value, row, index) {
                // console.log("edit:", row);
                if (row.carnExamState == "审核中") {
                    $.confirm({
                        closeIcon: true,
                        closeIconClass: 'fa fa-close',
                        columnClass: 'small',
                        title: '审核',
                        content: '确定要审核这条记录吗？',
                        buttons: {
                            取消: {
                                btnClass: 'btn-default',
                            },
                            确定: {
                                btnClass: 'btn-success',
                                action: function () {
                                    editTable(row);
                                }
                            },
                        }
                    });
                } else {
                    $.dialog("已经审核,不能再次审核");
                }
            }
        }

        var commonrow = {
                field: 'operate',
                title: '操作',
                width: 100,
                align: 'center',
                events: operateEvent,
                formatter: operateFormatter
            },
            state = {
                field: 'state',
                checkbox: 'true',
                align: 'center',
                valign: 'middle'
            };

        function operateFormatter(value, row, index) {
            return [
                '<a class="edit" href="javascript:void(0)" title="审核">',
                '审核',
                '</a> '
            ].join("");
        }

        tableColumn = [{
            field: 'carnId',
            visible: true,
            title: '需求编号'
        }, {
            field: 'carnType',
            visible: true,
            title: '车辆型号'
        }, {
            field: 'carnNum',
            visible: true,
            title: '车辆数目'
        }, {
            field: 'carnExamState',
            visible: true,
            title: '审核状态'
        }, {
            field: 'carExamPerson',
            visible: false,
            title: '审核人'
        }, {
            field: 'carnExamDept',
            visible: false,
            title: '审核部门'
        }, {
            field: 'carIds',
            visible: true,
            title: '车辆编号'
        }];

        carTableColumn = [{
            field: 'carId',
            visible: true,
            title: '车辆编号'
        }, {
            field: 'userId',
            visible: false,
            title: 'userId'
        }, {
            field: 'userTrueName',
            visible: false,
            title: '司机'
        }, {
            field: 'carLicense',
            visible: true,
            title: '车牌号'
        }, {
            field: 'carType',
            visible: true,
            title: '车辆类型'
        }, {
            field: 'carWeight',
            visible: false,
            title: '载重'
        }];

        /*表格加载*/
        $table.bootstrapTable({
            singleSelect: false, //设置True 将禁止多选
            striped: true, //设置隔行变色
            clickToSelect: true, //设置true 将在点击行时，自动选择rediobox 和 checkbox
            sortable: true, //是否启用排序
            sortOrder: 'asc', //定义排序方式 'asc' 或者 'desc'
            sortName: 'carnId', //定义排序列,通过url方式获取数据填写字段名，否则填写下标
            queryParams: function (params) { //用来向后台传请求参数,有queryParams就不用data:
                $.extend(params, {
                    "carnExamState": '"审核中","通过","拒绝"'
                }); //searchParams返回的是参数格式  return {N_id:abc}
                return params;
            },
            url: '/vehicle/searchVDemand', //请求接口
            columns: getColumns(tableColumn), //列数据,也可以通过函数来获取
            detailView: true, //详细查看按钮
            detailFormatter: detailFormatter, //显示详细查看数据
            onDblClickRow: function (row, $element, field) {
                editTable(row);
            },
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
        $("#searchBtn").click(function (e) {
            e.preventDefault();
            // console.log(searchParams());
            $table.bootstrapTable('refresh', {
                url: '/vehicle/searchVDemand',
                query: searchParams()
            });
        });

        function searchParams() {
            var sendParams = $("#searchForm").serializeArray();
            $.each(sendParams, function (index, val) {
                /* iterate through array or object */
                sendParams[val["name"]] = $.trim(val["value"]);
            });
            // console.log(sendParams);
            return sendParams;
        }

        function editTable(row) {
            $("#myModalLabel").text("修改");
            showModal();
            $.each(row, function (index, value) {
                // console.log(index, "", value);
                /* iterate through array or object */
                if (index == "carnType") {
                    if (value == "1800") {
                        $addForm.find(':input[name="carnType"]').val(value);
                    } else if (value == "6000") {
                        $addForm.find(':input[name="carnType"]').val(value);
                    } else if (value == "14000") {
                        $addForm.find(':input[name="carnType"]').val(value);
                    } else if (value == "20000") {
                        $addForm.find(':input[name="carnType"]').val(value);
                    }
                } else {
                    $addForm.find(":input[name=" + index + "]").val(value);
                }
            });
            $addForm.find(':input[name="carExamPerson"]').val(username);
            $carTable.bootstrapTable({
                sortable: true, //是否启用排序
                sortOrder: 'asc', //定义排序方式 'asc' 或者 'desc'
                sortName: 'carId', //定义排序列,通过url方式获取数据填写字段名，否则填写下标
                queryParams: function (params) { //用来向后台传请求参数,有queryParams就不用data:
                    $.extend(params, {
                        "carWeight": row.carnType,
                        "number": row.carnNum,
                        "isFree": "是"
                    });
                    return params;
                },
                url: '/vehicle/searchCarNeed', //请求接口
                columns: getCarColumns(carTableColumn),
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

            function getCarColumns(params) {
                var columns = [];
                columns.push(state);
                $.each(params, function (index, val) {
                    /* iterate through array or object */
                    var row = {};
                    row.field = val.field;
                    row.visible = val.visible;
                    row.title = val.title;
                    row.width = 100;
                    row.class = 'title-class';
                    row.sortable = true;
                    columns.push(row);
                });
                return columns;
            }

        }

        function showModal() {
            $modal.modal("show");
        }

        /*关闭重置表单*/
        $modal.on('hide.bs.modal', function () {
            $("#resetBtn").click();
            $('.selectAskPur').selectpicker('val', []);
        });
        $("#submitBtn").click(function (event) {
            /* 点击提交按钮 */
            submitData();
        });

        function submitData() {
            var formData = {};
            formData = $addForm.serializeArray();
            var selectTable = $carTable.bootstrapTable("getSelections");
            var carIds = "";
            $.each(selectTable, function (index, val) {
                /* iterate through array or object */
                carIds += val.carId + ",";
            });
            carIds = carIds.substring(0, carIds.length - 1);
            formData.push({"name": "carIds", "value": carIds});
            console.log("formData:", formData);
            Lprapm.Ajax.request({
                url: '/vehicle/insertVDemand',
                data: formData,
                success: function (response) {
                    if (response.success) {
                        $table.bootstrapTable("refresh");
                        $modal.modal('hide');
                    } else {
                        $.dialog(response.messages);
                    }
                }
            });
        }


    })
