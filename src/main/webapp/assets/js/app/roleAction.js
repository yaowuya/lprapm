/**
 * Created by Administrator on 2017/1/7.
 */
define(['ajaxPackage', 'angular', 'table', 'jqueryConfirm'],
    function(Lprapm) {
        var $roleTable = $(".role-table"),
            $actionTable = $(".action-table"),
            roleData = [],
            roleColumn = [],
            actionColumn = [],
            $addRoleForm = $("#addRoleForm"),
            $addActionForm = $("#addActionForm"),
            isRoleEdit = true,
            isActionEdit = true,
            isRoleFirst = true,
            isActionFirst = true,
            $roleModal = $("#roleModal"),
            $actionModal = $("#actionModal"),
            selectOption = {
                isSearch: true, //是否显示搜索框
                multiple: false, //是否多选
                width: '394px', //长度
                actionBox: true, //是否展示全选、取消按钮
                title: '请选择你的...', //默认提示
                dataSize: 5, //最多显示个数，数据多时会有滚动条
            };
        // 角色管理
            var operateEvent = { //要放在commonrow之前，因为是赋值函数，要置前
                'click .edit': function(event, value, row, index) {
                    // console.log("edit:", row);
                    editRoles(row);
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
                                        url: '/roleAction/deleteRole',
                                        data: {
                                            "roleId": row.roleId,
                                            "raId": row.raId
                                        },
                                        success: function(response) {
                                            if (response.success) {
                                                $roleTable.bootstrapTable("refresh");
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

            function editRoles(row) {
                isRoleEdit = true;
                $("#roleModalLabel").text("修改");
                showRoleModal();
                $.each(row, function(index, value) {
                    /* iterate through array or object */
                    $addRoleForm.find("input[name=" + index + "]").val(value);
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

            roleColumn = [{
                field: 'roleId',
                visible: true,
                title: '角色id'
            }, {
                field: 'roleName',
                visible: true,
                title: '角色名称'
            }, {
                field: 'roleDesc',
                visible: true,
                title: '角色描述'
            }, {
                field: 'raId',
                visible: false,
                title: 'roleActionId'
            }];
            /*表格加载*/
            $roleTable.bootstrapTable({
                singleSelect: false, //设置True 将禁止多选
                striped: true, //设置隔行变色
                clickToSelect: true, //设置true 将在点击行时，自动选择rediobox 和 checkbox
                sortable: true, //是否启用排序
                sortOrder: 'asc', //定义排序方式 'asc' 或者 'desc'
                sortName: 'roleId', //
                url: '/roleAction/searchRole', //请求接口
                columns: getColumns(roleColumn), //列数据,也可以通过函数来获取
                onDblClickRow: function(row, $element, field) {
                    editRoles(row);
                },
                onLoadSuccess: function(response) { //请求成功，返回数据
                    // 数据操作
                    // 自动显示过长的数据
                    console.log(response.data);
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
                /*点击角色查询按钮*/
            $("#roleNameBtn").click(function(e) {
                e.preventDefault();
                // console.log(searchParams());
                $roleTable.bootstrapTable('refresh', {
                    url: '/roleAction/searchRole',
                    query: searchParams()
                });
            });

            function searchParams() {
                var sendParams = {};
                var inputValue = $('#roleName').val();
                sendParams["roleName"] = $.trim(inputValue);
                return sendParams;
            }
            $("#btn-role-add").click(function() {
                /*点击角色添加按钮*/
                $("#roleModalLabel").text("添加");
                isRoleEdit = false;
                showRoleModal();
            });
            // 显示角色管理弹框
            function showRoleModal() {
                $roleModal.modal("show");
            }

            /*关闭重置表单*/
            $roleModal.on('hide.bs.modal', function() {
                $("#resetRoleBtn").click();
            });
            $("#submitRoleBtn").click(function(event) {
                /* 点击提交按钮 */
                submitRoleData();
            });

            function submitRoleData() {
                var formData = {};
                formData = $addRoleForm.serializeArray();
                // console.log("formData", formData);
                Lprapm.Ajax.request({
                    url: '/roleAction/' + (isRoleEdit ? 'editRole' : 'insertRole'),
                    data: formData,
                    success: function(response) {
                        if (response.success) {
                            $roleTable.bootstrapTable("refresh");
                            $roleModal.modal('hide');
                        } else {
                            $.dialog(response.messages);
                        }
                    }
                });
            }
            /*权限管理start*/
            var operateEventa = { //要放在commonrow之前，因为是赋值函数，要置前
                'click .edit': function(event, value, row, index) {
                    // console.log("edit:", row);
                    editActions(row);
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
                                        url: '/roleAction/deleteAction',
                                        data: {
                                            "actionId": row.actionId
                                        },
                                        success: function(response) {
                                            if (response.success) {
                                                $actionTable.bootstrapTable("refresh");
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

            function editActions(row) {
                isActionEdit = true;
                $("#actionModalLabel").text("修改");
                showActionModal();
                $.each(row, function(index, value) {
                    /* iterate through array or object */
                    $addActionForm.find("input[name=" + index + "]").val(value);
                });
            }
            var statea = {
                    field: 'state',
                    checkbox: 'true',
                    align: 'center',
                    valign: 'middle'
                },
                commonrowa = {
                    field: 'operate',
                    title: '操作',
                    width: 100,
                    align: 'center',
                    events: operateEventa,
                    formatter: operateFormattera
                };

            function operateFormattera(value, row, index) {
                return [
                    '<a class="edit" href="javascript:void(0)" title="编辑">',
                    '<i class="glyphicon glyphicon-edit"></i>',
                    '</a> ',
                    '<a class="remove" href="javascript:void(0)" title="删除">',
                    '<i class="glyphicon glyphicon-remove"></i>',
                    '</a>'
                ].join("");
            }

            actionColumn = [{
                field: 'actionId',
                visible: true,
                title: '权限id'
            }, {
                field: 'actionName',
                visible: true,
                title: '权限名称'
            }, {
                field: 'actionDesc',
                visible: true,
                title: '权限描述'
            }];
            /*表格加载*/
            $actionTable.bootstrapTable({
                singleSelect: false, //设置True 将禁止多选
                striped: true, //设置隔行变色
                clickToSelect: true, //设置true 将在点击行时，自动选择rediobox 和 checkbox
                sortable: true, //是否启用排序
                sortOrder: 'asc', //定义排序方式 'asc' 或者 'desc'
                sortName: 'actionId', //
                url: '/roleAction/searchAction', //请求接口
                columns: getColumnsa(actionColumn), //列数据,也可以通过函数来获取
                onDblClickRow: function(row, $element, field) {
                    editActions(row);
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

            function getColumnsa(params) {
                    var columns = [];
                    columns.push(statea);
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
                    columns.push(commonrowa);
                    return columns;
                }
                /*点击角色查询按钮*/
            $("#actionNameBtn").click(function(e) {
                e.preventDefault();
                // console.log(searchParams());
                $actionTable.bootstrapTable('refresh', {
                    url: '/roleAction/searchAction',
                    query: searchParamsa()
                });
            });

            function searchParamsa() {
                var sendParams = {};
                var inputValue = $('#actionName').val();
                sendParams["actionName"] = $.trim(inputValue);
                return sendParams;
            }
            $("#btn-action-add").click(function() {
                /*点击角色添加按钮*/
                $("#actionModalLabel").text("添加");
                isActionEdit = false;
                showActionModal();
            });
            // 显示角色管理弹框
            function showActionModal() {
                $actionModal.modal("show");
            }

            /*关闭重置表单*/
            $actionModal.on('hide.bs.modal', function() {
                $("#resetActionBtn").click();
            });
            $("#submitActionBtn").click(function(event) {
                /* 点击提交按钮 */
                submitActionData();
            });

            function submitActionData() {
                    var formData = {};
                    formData = $addActionForm.serializeArray();
                    console.log("formData", formData);
                    Lprapm.Ajax.request({
                        url: '/roleAction/' + (isActionEdit ? 'editAction' : 'insertAction'),
                        data: formData,
                        success: function(response) {
                            if (response.success) {
                                $actionTable.bootstrapTable("refresh");
                                $actionModal.modal('hide');
                            } else {
                                $.dialog(response.messages);
                            }
                        }
                    });
                }
                /*权限管理end*/
    });