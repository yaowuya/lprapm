define(['ajaxPackage', 'timePicker', 'select',
        'table', 'jqueryConfirm'
    ],
    function (Lprapm, timePicker, Select) {
        var outCar = function () {
            var $table = $(".OC-table"),
                tableColumn = [];
            timePicker.picker("#createOCTime", "#endOCTime");
            var operateEvent = { //要放在commonrow之前，因为是赋值函数，要置前
                'click .edit': function (event, value, row, index) {
                    // console.log("remove:", row);
                    if (row.carnExamState == "通过") {
                        $.confirm({
                            closeIcon: true,
                            closeIconClass: 'fa fa-close',
                            columnClass: 'small',
                            title: '出车登记',
                            content: '确定要出车吗？',
                            buttons: {
                                取消: {
                                    btnClass: 'btn-default',
                                },
                                确定: {
                                    btnClass: 'btn-success',
                                    action: function () {
                                        Lprapm.Ajax.request({
                                            url: '/carscheme/surePOS',
                                            data: {
                                                "carplanId": row.carplanId,
                                                "orderIds": row.orderIds,
                                                "carnId": row.carnId
                                            },
                                            success: function (response) {
                                                if (response.success) {
                                                    $table.bootstrapTable("refresh");
                                                } else {
                                                    $.dialog('出车失败');
                                                }
                                            }
                                        });
                                    }
                                },
                            }
                        });
                    } else {
                        $.dialog("已经出车，不能再次登记");
                    }
                }
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
                    '<a class="edit" href="javascript:void(0)" title="出车登记" style="color:red;">',
                    '出车登记',
                    '</a> '
                ].join("");
            }

            tableColumn = [{
                field: 'carplanId',
                visible: true,
                title: '配送单编号',
            }, {
                field: 'orderIds',
                visible: false,
                title: '物流id'
            }, {
                field: 'orderNames',
                visible: true,
                title: '货物名称'
            }, {
                field: 'carnId',
                visible: false,
                title: '配车Id'
            }, {
                field: 'allWeight',
                visible: true,
                title: '总重量'
            }, {
                field: 'allNumber',
                visible: true,
                title: '总数量'
            }, {
                field: 'allVolume',
                visible: true,
                title: '总体积'
            }, {
                field: 'carnExamState',
                visible: true,
                title: '出车状态'
            }, {
                field: 'carnNum',
                visible: true,
                title: '车数'
            }, {
                field: 'carnType',
                visible: true,
                title: '车类型'
            }, {
                field: 'carplanDept',
                visible: true,
                title: '配车部门'
            }, {
                field: 'carplanPerson',
                visible: true,
                title: '配车人'
            }, {
                field: 'carplanDesrciption',
                visible: true,
                title: '配车描述'
            }, {
                field: 'provinceid',
                visible: false,
                title: '目的省份id'
            }, {
                field: 'cityid',
                visible: false,
                title: '目的地级市id'
            }, {
                field: 'areaid',
                visible: false,
                title: '目的县级市id'
            }, {
                field: 'province',
                visible: true,
                title: '目的省份'
            }, {
                field: 'city',
                visible: true,
                title: '目的地级市'
            }, {
                field: 'area',
                visible: true,
                title: '目的县级市'
            }, {
                field: 'carplanTime',
                visible: true,
                title: '创建时间'
            }];

            /*表格加载*/
            $table.bootstrapTable({
                singleSelect: false, //设置True 将禁止多选
                striped: true, //设置隔行变色
                clickToSelect: true, //设置true 将在点击行时，自动选择rediobox 和 checkbox
                sortable: true, //是否启用排序
                sortOrder: 'asc', //定义排序方式 'asc' 或者 'desc'
                sortName: 'carplanId', //定义排序列,通过url方式获取数据填写字段名，否则填写下标
                queryParams: function (params) { //用来向后台传请求参数,有queryParams就不用data:
                    $.extend(params, {
                        "carnExamState": '"通过","出发","到站"'
                    }); //searchParams返回的是参数格式  return {N_id:abc}
                    return params;
                },
                url: '/carscheme/searchPOS', //请求接口
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
                columns.push(state);
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
            $("#searchOCBtn").click(function (e) {
                e.preventDefault();
                // console.log(searchParams());
                $table.bootstrapTable('refresh', {
                    url: '/carscheme/searchPOS',
                    query: searchParams()
                });
            });

            function searchParams() {
                var sendParams = {},
                    selectData = $("#searchOCForm").find(':input');
                $.each(selectData, function (index, val) {
                    /* iterate through array or object */
                    sendParams[$(this).attr("name")] = $.trim($(this).val());
                });
                return sendParams;
            }

        }

        return {
            "outCar": outCar
        }
    })