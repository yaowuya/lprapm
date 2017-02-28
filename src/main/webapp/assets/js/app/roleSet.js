/**
 * Created by Administrator on 2017/1/16.
 */
define(['ajaxPackage', 'select', 'angular', 'table', 'jqueryConfirm'], function(Lprapm, selectPackage) {
    var contentLi = $(".content-nav .nav li"),
        tableColumn = [],
        $table = '',
        $form = '',
        $modal = '',
        $selectA = '',
        $selectB = '',
        selectOption = {
            isSearch: true, //是否显示搜索框
            multiple: false, //是否多选
            width: '394px', //长度
            actionBox: true, //是否展示全选、取消按钮
            title: '请选择你的...', //默认提示
            dataSize: 5, //最多显示个数，数据多时会有滚动条
        };
    onLoadFirstA();

    function onLoadFirstA() {
        var isEdit = true,
            isFirst = true;
        $table = $(".roleUser-table");
        $modal = $("#roleUserModal");
        $form = $("#addroleUserForm");
        $selectA = $(".selectRoleUser");
        $selectB = $(".selectUser");
        tableColumn = [{
            field: 'userId',
            visible: true,
            title: '用户id'
        }, {
            field: 'roleId',
            visible: true,
            title: '角色id'
        }, {
            field: 'userName',
            visible: true,
            title: '用户名称'
        }, {
            field: 'userEmail',
            visible: true,
            title: '用户邮箱'
        }, {
            field: 'roleName',
            visible: true,
            title: '角色名称'
        }, {
            field: 'Id',
            visible: false,
            title: 'userRoleId'
        }];
        roleSetting($table, tableColumn, $modal, $form, isEdit, isFirst, $selectA, $selectB, "Id", "Role", "User");
    }
    contentLi.on('click', 'a', function(event) {
        event.preventDefault();
        /* Act on the event */
        var ahref = $(this).attr('href').substring(1);
        if (ahref == 'roleUser') {
            onLoadFirstA();
        } else if (ahref == 'roleAction') {
            var isEdit = true,
                isFirst = true;
            $table = $(".roleAction-table");
            $modal = $("#roleActionModal");
            $form = $("#addroleActionForm");
            $selectA = $(".selectRoleAction");
            $selectB = $(".selectAction");
            tableColumn = [{
                field: 'roleId',
                visible: true,
                title: '角色id'
            }, {
                field: 'actionId',
                visible: true,
                title: '权限id'
            }, {
                field: 'roleName',
                visible: true,
                title: '角色名称'
            }, {
                field: 'actionName',
                visible: true,
                title: '权限名称'
            }, {
                field: 'Id',
                visible: false,
                title: 'roleActionId'
            }];
            roleSetting($table, tableColumn, $modal, $form, isEdit, isFirst, $selectA, $selectB, "Id", "Role", "Action");
        } else if (ahref == 'roleMenu') {
            var isEdit = true,
                isFirst = true;
            $table = $(".roleMenu-table");
            $modal = $("#roleMenuModal");
            $form = $("#addroleMenuForm");
            $selectA = $(".selectRoleMenu");
            $selectB = $(".selectMenu");
            tableColumn = [{
                field: 'roleId',
                visible: true,
                title: '角色id'
            }, {
                field: 'menuId',
                visible: true,
                title: '菜单id'
            }, {
                field: 'roleName',
                visible: true,
                title: '角色名称'
            }, {
                field: 'menuName',
                visible: true,
                title: '菜单名称'
            }, {
                field: 'Id',
                visible: false,
                title: 'roleMenuId'
            }];
            roleSetting($table, tableColumn, $modal, $form, isEdit, isFirst, $selectA, $selectB, "Id", "Role", "Menu");
        } else {

        }

    });
    /**
     * [roleSetting description]
     * @param  {[type]}  $table      [description]
     * @param  {[type]}  tableColumn [description]
     * @param  {[type]}  $modal      [description]
     * @param  {Boolean} isEdit      [description]
     * @param  {Boolean} isFirst     [description]
     * @param  {[type]}  $selectA    [description]
     * @param  {[type]}  $selectB    [description]
     * @param  {[type]}  rowId       [description]
     * @param  {[type]}  setNameA    [description]
     * @param  {[type]}  setNameB    [description]
     * @return {[type]}              [description]
     * $select* 和setName*相互对应
     */
    function roleSetting($table, tableColumn, $modal, $form, isEdit, isFirst, $selectA, $selectB, rowId, setNameA, setNameB) {
        var aSet = setNameA.toLowerCase(),
            bSet = setNameB.toLowerCase();
        // console.log("$selectA:",$selectA," ","$selectB:",$selectB)
        var operateEvent = { //要放在commonrow之前，因为是赋值函数，要置前
            'click .edit': function(event, value, row, index) {
                // console.log("edit:", row);
                updateRow(row);
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
                                    url: '/roleSet/delete' + setNameA + setNameB,
                                    data: {
                                        "Id": row.Id
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

        function updateRow(row) {
            var aId = aSet + 'Id',
                bId = bSet + 'Id';
            isEdit = true;
            $modal.find('h4.modal-title').text("修改");
            showModal();
            $.each(row, function(index, value) {
                /* iterate through array or object */
                if (index == aId) {
                    var selectVaue = [];
                    selectVaue.push(value);
                    selectPackage.selectList.select($selectA, selectVaue);
                } else if (index == bId) {
                    var selectVaue = [];
                    selectVaue.push(value);
                    selectPackage.selectList.select($selectB, selectVaue);
                } else {
                    $form.find("input[name=" + index + "]").val(value);
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
            /*表格加载*/
        $table.bootstrapTable({
            singleSelect: false, //设置True 将禁止多选
            striped: true, //设置隔行变色
            clickToSelect: true, //设置true 将在点击行时，自动选择rediobox 和 checkbox
            sortable: true, //是否启用排序
            sortOrder: 'asc', //定义排序方式 'asc' 或者 'desc'
            sortName: rowId, //
            url: '/roleSet/search' + setNameA + setNameB, //请求接口
            columns: getColumns(tableColumn), //列数据,也可以通过函数来获取
            onDblClickRow: function(row, $element, field) {
                updateRow(row);
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
        $("#" + setNameA + setNameB + 'Btn').click(function(e) {
            e.preventDefault();
            var searchForm = $(this).closest('form');
            // console.log(searchParams());
            $table.bootstrapTable('refresh', {
                url: '/roleSet/search' + setNameA + setNameB,
                query: searchParams(searchForm)
            });
        });

        function searchParams(searchForm) {
            var sendParams = searchForm.serializeArray();
            $.each(sendParams, function(index, val) {
                /* iterate through array or object */
                sendParams[val["name"]] = $.trim(val["value"]);
            });
            return sendParams;
        }
        $("#" + setNameA + setNameB + "Add").click(function() {
            /*点击角色添加按钮*/
            $modal.find('h4.modal-title').text("添加");
            isEdit = false;
            isFirst || selectPackage.selectList.select($selectB, []);
            isFirst || selectPackage.selectList.select($selectA, []);
            showModal();
        });
        // 显示角色管理弹框
        function showModal() {
            if (isFirst) {
                isFirst = false;
                /*加载父级id下拉框*/
                selectPackage.selectList.option($selectB, selectOption, '/roleSet/search' + bSet, bSet + 'Name', bSet + 'Id', []);
                selectPackage.selectList.option($selectA, selectOption, '/roleSet/search' + aSet, aSet + 'Name', aSet + 'Id', []);

            }
            $modal.modal("show");
        }

        /*关闭重置表单*/
        $modal.on('hide.bs.modal', function() {
            $("#reset" + setNameA + setNameB + "Btn").click();
        });
        $("#submit" + setNameA + setNameB + "Btn").click(function(event) {
            /* 点击提交按钮 */
            submitData();
        });

        function submitData() {
            var formData = {};
            formData = $form.serializeArray();
            // console.log("formData", formData);
            Lprapm.Ajax.request({
                url: '/roleSet/' + (isEdit ? 'edit' + setNameA + setNameB : 'insert' + setNameA + setNameB),
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

});