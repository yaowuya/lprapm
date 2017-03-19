define(['ajaxPackage', 'select',
        'package/address/selectProvince',
        'package/address/selectCity',
        'package/address/selectArea',
        'table', 'jqueryConfirm'
    ],
    function (Lprapm, Select, SelectPro, SelectCity, SelectArea) {

        var $table = $(".table"),
            $modal = $("#myModal"),
            $addForm = $("#addForm"),
            $province = $("#rptProvince"),
            $city = $("#rptCity"),
            $area = $("#rptArea"),
            $formPro = $("#formProvince"),
            $formCity = $("#formCity"),
            $formArea = $("#formArea"),
            isEdit = true,
            tableColumn = [],
            selectOption = {
                isSearch: true, //是否显示搜索框
                multiple: false, //是否多选
                width: '95%', //长度
                actionBox: true, //是否展示全选、取消按钮
                title: '请选择...', //默认提示
                dataSize: 5, //最多显示个数，数据多时会有滚动条
            };
        Select.selectList.option($province, selectOption, '/address/province', "province", "provinceid", []);
        $city.selectpicker('show');
        $area.selectpicker('show');

        SelectPro.selectList.option($formPro, selectOption, []);
        SelectCity.selectList.option($formCity, selectOption, []);
        SelectArea.selectList.option($formArea, selectOption, []);

        $province.on('change', function (event) {
            event.preventDefault();
            /* Act on the event */
            var provinceVal = $(this).val();
            Select.selectList.option($city, selectOption, "/address/city", "city", "cityid", [], {
                "provinceid": provinceVal
            });
        });
        $formPro.on('change', function (event) {
            event.preventDefault();
            /* Act on the event */
            var provinceVal = $(this).val();
            SelectCity.selectList.option($formCity, selectOption, [], {
                "provinceid": provinceVal
            });
        });
        $city.on('change', function (event) {
            event.preventDefault();
            /* Act on the event */
            if ($province.val() == null || $province.val() == "") {
                $city.selectpicker('destroy');
            } else {
                var cityVal = $(this).val();
                Select.selectList.option($area, selectOption, "/address/area", "area", "areaid", [], {
                    "cityid": cityVal
                });
            }
        });
        $formCity.on('change', function (event) {
            event.preventDefault();
            /* Act on the event */
            if ($formPro.val() == null || $formPro.val() == "") {
                SelectCity.selectList.select($formCity, []);
            } else {
                var cityVal = $(this).val();
                SelectArea.selectList.option($formArea, selectOption, [], {
                    "cityid": cityVal
                });
            }
        });
        $area.on('change', function (event) {
            event.preventDefault();
            /* Act on the event */
            if ($city.val() == null || $city.val() == "") {
                $area.selectpicker('destroy');
            }
        });
        $formArea.on('change', function (event) {
            event.preventDefault();
            /* Act on the event */
            if ($formCity.val() == null || $formCity.val() == "") {
                SelectArea.selectList.select($formArea, []);
            }
        });
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
                                    url: '/repertory/deleteReper',
                                    data: {
                                        "repoId": row.repoId,
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
                if (index == "provinceid") {
                    var selectVaue = [];
                    selectVaue.push(value);
                    SelectPro.selectList.select($formPro, selectVaue);
                } else if (index == "cityid") {
                    var selectVaue = [];
                    selectVaue.push(value);
                    SelectCity.selectList.select($formCity, selectVaue);
                } else if (index == "areaid") {
                    var selectVaue = [];
                    selectVaue.push(value);
                    SelectArea.selectList.select($formArea, selectVaue);
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
            field: '仓储id',
            visible: true,
            title: 'repoId'
        }, {
            field: 'provinceid',
            visible: false,
            title: 'provinceid'
        }, {
            field: 'province',
            visible: true,
            title: '省份'
        }, {
            field: 'cityid',
            visible: false,
            title: 'cityid'
        }, {
            field: 'city',
            visible: true,
            title: '地级市'
        }, {
            field: 'areaid',
            visible: false,
            title: 'areaid'
        }, {
            field: 'area',
            visible: true,
            title: '市,地级市'
        }, {
            field: 'repoAddress',
            visible: true,
            title: '街道地址'
        }];
        /*表格加载*/
        $table.bootstrapTable({
            singleSelect: false, //设置True 将禁止多选
            striped: true, //设置隔行变色
            clickToSelect: true, //设置true 将在点击行时，自动选择rediobox 和 checkbox
            sortable: true, //是否启用排序
            sortOrder: 'asc', //定义排序方式 'asc' 或者 'desc'
            sortName: 'repoId', //定义排序列,通过url方式获取数据填写字段名，否则填写下标
            url: '/repertory/searchReper', //请求接口
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
                url: '/repertory/searchReper',
                query: searchParams()
            });
        });

        function searchParams() {
            var sendParams = {},
                selectData = $("#searchForm").find('select');
            $.each(selectData, function (index, val) {
                /* iterate through array or object */
                sendParams[$(this).attr("name")] = $.trim($(this).val());
            });
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
            SelectPro.selectList.select($formPro, []);
            SelectCity.selectList.select($formCity, []);
            SelectArea.selectList.select($formArea, []);
        });
        $("#submitBtn").click(function (event) {
            /* 点击提交按钮 */
            submitData();
        });

        function submitData() {
            var formData = {};
            formData = $addForm.serializeArray();
            Lprapm.Ajax.request({
                url: '/repertory/' + (isEdit ? 'updateReper' : 'insertReper'),
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