define(['ajaxPackage', 'select', 'table', 'jqueryConfirm'],
    function (Lprapm, Select) {

        var $table = $(".table"),
            $modal = $("#myModal"),
            $addForm = $("#addForm"),
            isEdit = true,
            tableColumn = [],
            $select = $(".selectUser"),
            selectOption = {
                isSearch: true, //是否显示搜索框
                multiple: false, //是否多选
                width: '95%', //长度
                actionBox: true, //是否展示全选、取消按钮
                title: '请选择你的...', //默认提示
                dataSize: 5, //最多显示个数，数据多时会有滚动条
            };

        Select.selectList.option($select, selectOption, '/vehicle/searchUser', "userTrueName", "userId", []);
        $('.selectAskPur').selectpicker('show');
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
                    title: '删除',
                    content: '确定要删除这条记录吗？',
                    buttons: {
                        取消: {
                            btnClass: 'btn-default',
                        },
                        确定: {
                            btnClass: 'btn-success',
                            action: function () {
                                Lprapm.Ajax.request({
                                    url: '/vehicle/deleteVehicle',
                                    data: {
                                        "carId": row.carId,
                                    },
                                    success: function (response) {
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
            isEdit = true;
            $("#myModalLabel").text("修改");
            showModal();
            $.each(row, function (index, value) {
                /* iterate through array or object */
                if (index == "userId") {
                    var selectVaue = [];
                    selectVaue.push(value);
                    Select.selectList.select($select, selectVaue);
                } else {
                    $addForm.find(":input[name=" + index + "]").val(value);
                }
            });
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
            field: 'carId',
            visible: true,
            title: 'carId'
        }, {
            field: 'userId',
            visible: false,
            title: 'userId'
        }, {
            field: 'userTrueName',
            visible: true,
            title: '司机'
        }, {
            field: 'carLicense',
            visible: true,
            title: '车牌号'
        }, {
            field: 'carType',
            visible: true,
            title: '车类型'
        }, {
            field: 'carVolume',
            visible: false,
            title: '车体积'
        }, {
            field: 'carWeight',
            visible: true,
            title: '车载重'
        }, {
            field: 'kmPrice',
            visible: true,
            title: '车费(元/km)'
        }, {
            field: 'isFree',
            visible: true,
            title: '是否空闲'
        }];
        /*表格加载*/
        $table.bootstrapTable({
            singleSelect: false, //设置True 将禁止多选
            striped: true, //设置隔行变色
            clickToSelect: true, //设置true 将在点击行时，自动选择rediobox 和 checkbox
            sortable: true, //是否启用排序
            sortOrder: 'asc', //定义排序方式 'asc' 或者 'desc'
            sortName: 'carId', //定义排序列,通过url方式获取数据填写字段名，否则填写下标
            url: '/vehicle/searchVehicle', //请求接口
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
                url: '/vehicle/searchVehicle',
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

        $("#insertBtn").click(function () {
            /*点击添加*/
            $("#myModalLabel").text("添加");
            isEdit = false;
            showModal();
        });

        function showModal() {
            $modal.modal("show");
        }

        /*关闭重置表单*/
        $modal.on('hide.bs.modal', function () {
            $("#resetBtn").click();
        });
        $("#submitBtn").click(function (event) {
            /* 点击提交按钮 */
            submitData();
        });

        function submitData() {
            var formData = {};
            formData = $addForm.serializeArray();
            Lprapm.Ajax.request({
                url: '/vehicle/' + (isEdit ? 'updateVehicle' : 'insertVehicle'),
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