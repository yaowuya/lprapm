/**
 * Created by zhongrf on 2017/1/10.
 */
define(['ajaxPackage', 'jqueryValidate', 'angular', 'table', 'jqueryConfirm'],
    function(Lprapm, Validate) {
        var $table = $(".dept-table"),
            tableData = [],
            tableColumn = [],
            $addForm = $("#addDeptForm"),
            isEdit = true,
            isFisrt = true,
            $modal = $("#myModal");
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
                                    url: '/department/deleteDept',
                                    data: {
                                        "deptId": row.deptId
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
            isEdit = true;
            $("#myModalLabel").text("修改");
            showModal();
            $.each(row, function(index, value) {
                /* iterate through array or object */
                $addForm.find("input[name=" + index + "]").val(value);
            });
        }
        var state = {
                field: 'state',
                checkbox: 'true',
                align: 'center',
                valign: 'middle'
            },
            commonrow = {
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
            field: 'deptId',
            visible: true,
            title: '部门id'
        }, {
            field: 'deptName',
            visible: true,
            title: '部门名称'
        }, {
            field: 'deptDesc',
            visible: true,
            title: '部门描述'
        }];
        /*表格加载*/
        $table.bootstrapTable({
            singleSelect: false, //设置True 将禁止多选
            striped: true, //设置隔行变色
            clickToSelect: true, //设置true 将在点击行时，自动选择rediobox 和 checkbox
            sortable: true, //是否启用排序
            sortOrder: 'asc', //定义排序方式 'asc' 或者 'desc'
            sortName: 'deptId', //定义排序列,通过url方式获取数据填写字段名，否则填写下标
            url: '/department/searchDept', //请求接口
            columns: getColumns(tableColumn), //列数据,也可以通过函数来获取
            onDblClickRow: function(row, $element, field) {
                editTable(row);
            },
            onLoadSuccess: function(response) { //请求成功，返回数据
                // 数据操作
                // 自动显示过长的数据
                var arrow = $(".title-class");
                $.each(arrow, function(index, val) {
                    /* iterate through array or object */
                    $(this).attr("title", val.innerText);
                });
            }
        });

        function getColumns(params) {
            var columns = [];
            columns.push(state);
            $.each(params, function(index, val) {
                /* iterate through array or object */
                var row = {};
                row.field = val.field;
                row.visible = val.visible;
                row.title = val.title;
                // row.width=150;
                row.class = 'title-class';
                row.sortable = true;
                columns.push(row);
            });
            columns.push(commonrow);
            return columns;
        }

        /*点击查询按钮*/
        $("#deptNameBtn").click(function(e) {
            e.preventDefault();
            // console.log(searchParams());
            $table.bootstrapTable('refresh', {
                url: '/department/searchDept',
                query: searchParams()
            });
        });

        function searchParams() {
            var sendParams = {};
            var inputValue = $('#deptName').val();
            sendParams["deptName"] = $.trim(inputValue);
            return sendParams;
        }
        $("#btn-dept-add").click(function() {
            /*点击添加*/
            $("#myModalLabel").text("添加");
            isEdit = false;
            showModal();
        });

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
                url: '/department/' + (isEdit ? 'editDept' : 'insertDept'),
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

    });