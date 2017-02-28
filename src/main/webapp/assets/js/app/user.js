/**
 * Created by Administrator on 2017/1/8.
 */
define(['ajaxPackage', 'moment', 'datepicker', 'jqueryValidate', 'angular', 'table', 'jqueryConfirm'],
    function(Lprapm, moment) {
        var $table = $(".user-table"),
            tableColumn = [],
            $addForm = $("#addUserForm"),
            isEdit = true,
            isFisrt = true,
            $modal = $("#myModal");
        var picker = $('#birthDay').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: moment.locale('zh-cn'),
            dayViewHeaderFormat: 'MM YYYY',
            stepping: '2',
            showTodayButton: true,
            useCurrent: false,
        });
        picker.on('dp.change', function(e) {
            picker.data('DateTimePicker').hide();
        });
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
                                    url: '/user/deleteUser',
                                    data: {
                                        "userId": row.userId,
                                        "urId": row.urId
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
                if (index == "userSex") {
                    if (value == "man") {
                        $addForm.find("#manOption").attr('checked', 'true');
                        $addForm.find("#womanOption").removeAttr('checked');
                    }
                    if (value == "woman") {
                        $addForm.find("#manOption").removeAttr('checked');
                        $addForm.find("#womanOption").attr('checked', 'true');
                    }
                } else {
                    $addForm.find("input[name=" + index + "]").val(value);
                }
            });
        }
        var commonrow = {
            field: 'operate',
            title: '操作',
            visible: true,
            width: '100px',
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
            field: 'userId',
            visible: true,
            title: '用户id'
        }, {
            field: 'userEmail',
            visible: true,
            title: '账号'
        }, {
            field: 'userPassword',
            visible: true,
            title: '密码'
        }, {
            field: 'userTrueName',
            visible: true,
            title: '真实姓名'
        }, {
            field: 'userPhone',
            visible: true,
            title: '电话号码'
        }, {
            field: 'userBirthday',
            visible: true,
            title: '生日'
        }, {
            field: 'userSex',
            visible: true,
            title: '性别'
        }, {
            field: 'userCompany',
            visible: true,
            title: '所在公司'
        }, {
            field: 'userDept',
            visible: true,
            title: '所在部门'
        }, {
            field: 'userDeptPhone',
            visible: true,
            title: '部门电话'
        }, {
            field: 'userDeptDesc',
            visible: true,
            title: '部门描述'
        }, {
            field: 'urId',
            visible: false,
            title: 'UserRoleId'
        }];
        /*表格加载*/
        $table.bootstrapTable({
            singleSelect: false, //设置True 将禁止多选
            striped: true, //设置隔行变色
            clickToSelect: true, //设置true 将在点击行时，自动选择rediobox 和 checkbox
            sortable: true, //是否启用排序
            sortOrder: 'asc', //定义排序方式 'asc' 或者 'desc'
            sortName: 'menuId', //定义排序列,
            url: '/user/searchUser', //请求接口
            columns: getColumns(tableColumn), //列数据,也可以通过函数来获取
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
            $.each(params, function(index, val) {
                /* iterate through array or object */
                var row = {};
                row.field = val.field;
                row.visible = val.visible;
                row.title = val.title;
                row.width = "100";
                row.class = 'title-class';
                row.sortable = true;
                columns.push(row);
            });
            columns.push(commonrow);
            return columns;
        }

        /*点击查询按钮*/
        $("#userNameBtn").click(function(e) {
            e.preventDefault();
            // console.log(searchParams());
            $table.bootstrapTable('refresh', {
                url: '/user/searchUser',
                query: searchParams()
            });
        });

        function searchParams() {
            var sendParams = {};
            var inputValue = $('#userEmail').val();
            sendParams["userEmail"] = $.trim(inputValue);
            return sendParams;
        }
        $("#btn-user-add").click(function() {
            /*点击添加*/
            $("#myModalLabel").text("添加");
            isEdit = false;
            showModal();
        });

        function showModal() {
            $modal.modal("show");
            // 设置弹框高度
            var bodyHeight = window.screen.height;
            $modal.find('.modal-body').css({
                overflowY:'scroll',
                height: bodyHeight - 500 + 'px'
            });
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
            // console.log("formData:", formData);
            Lprapm.Ajax.request({
                url: '/user/' + (isEdit ? 'editUser' : 'insertUser'),
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
