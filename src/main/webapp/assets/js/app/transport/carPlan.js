define(['ajaxPackage', 'timePicker', 'select',
        'package/address/selectProvince',
        'package/address/selectCity',
        'package/address/selectArea',
        'table', 'jqueryConfirm'
    ],
    function (Lprapm, timePicker, Select, SelectPro, SelectCity, SelectArea) {
        var carPlan = function () {
            var $table = $(".CP-table"),
                $modal = $("#myModal"),
                $addForm = $("#addCPForm"),
                $province = $("#CPProvince"),
                $city = $("#CPCity"),
                $area = $("#CPArea"),
                isEdit = true,
                tableColumn = [],
                selectOption = {
                    isSearch: true, //是否显示搜索框
                    multiple: false, //是否多选
                    width: '100%', //长度
                    actionBox: true, //是否展示全选、取消按钮
                    title: '请选择...', //默认提示
                    dataSize: 5, //最多显示个数，数据多时会有滚动条
                };
            var username = sessionStorage.getItem("User");
            /*显示下拉框*/
            $('.selectAskPur').selectpicker('show');
            timePicker.picker("#createCPTime", "#endCPTime");
            SelectPro.selectList.option($province, selectOption, []);
            SelectCity.selectList.option($city, selectOption, []);
            SelectArea.selectList.option($area, selectOption, []);


            $province.on('change', function (event) {
                event.preventDefault();
                /* Act on the event */
                var provinceVal = $(this).val();
                SelectCity.selectList.option($city, selectOption, [], {
                    "provinceid": provinceVal
                });
            });

            $city.on('change', function (event) {
                event.preventDefault();
                /* Act on the event */
                if ($province.val() == null || $province.val() == "") {
                    SelectCity.selectList.select($city, []);
                } else {
                    var cityVal = $(this).val();
                    SelectArea.selectList.option($area, selectOption, [], {
                        "cityid": cityVal
                    });
                }
            });

            $area.on('change', function (event) {
                event.preventDefault();
                /* Act on the event */
                if ($city.val() == null || $city.val() == "") {
                    SelectArea.selectList.select($area, []);
                }
            });

            Lprapm.Ajax.request({
                url: '/carscheme/searchAddress',
                success: function (response) {
                    if (response.success) {
                        $(".content-theme p").text(response.data);
                    } else {
                        $.dialog(response.messages);
                    }
                }
            });

            var state = {
                field: 'state',
                checkbox: 'true',
                align: 'center',
                valign: 'middle'
            };
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
                field: 'provinceid',
                visible: false,
                title: '订单所在省份id'
            }, {
                field: 'cityid',
                visible: false,
                title: '订单所在地级市id'
            }, {
                field: 'areaid',
                visible: false,
                title: '收货县级市id'
            }, {
                field: 'receiptProvinceid',
                visible: false,
                title: '收货省份id'
            }, {
                field: 'receiptCityid',
                visible: false,
                title: '收货地级市id'
            }, {
                field: 'receiptAreaid',
                visible: false,
                title: '收货县级市id'
            }, {
                field: 'receiptProvince',
                visible: false,
                title: '收货省份'
            }, {
                field: 'receiptCity',
                visible: false,
                title: '收货地级市'
            }, {
                field: 'receiptArea',
                visible: false,
                title: '收货县级市'
            }, {
                field: 'receiptAddress',
                visible: false,
                title: '收货地址'
            }, {
                field: 'isAskLog',
                visible: true,
                title: '是否物流询价'
            }, {
                field: 'oeState',
                visible: false,
                title: '审核状态'
            }, {
                field: 'oeDept',
                visible: false,
                title: '审核部门'
            }, {
                field: 'oePerson',
                visible: false,
                title: '审核人'
            }, {
                field: 'oeReason',
                visible: false,
                title: '审核原因'
            }, {
                field: 'logState',
                visible: true,
                title: '物流状态'
            }, {
                field: 'logDept',
                visible: false,
                title: '审核部门'
            }, {
                field: 'logPerson',
                visible: false,
                title: '审核人'
            }, {
                field: 'logPrice',
                visible: true,
                title: '物流报价'
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
            }, {
                field: 'oeId',
                visible: false,
                title: 'oeId'
            }];
            /*表格加载*/
            $table.bootstrapTable({
                singleSelect: false, //设置True 将禁止多选
                striped: true, //设置隔行变色
                clickToSelect: true, //设置true 将在点击行时，自动选择rediobox 和 checkbox
                sortable: true, //是否启用排序
                sortOrder: 'asc', //定义排序方式 'asc' 或者 'desc'
                sortName: 'orderId', //定义排序列,通过url方式获取数据填写字段名，否则填写下标
                queryParams: function (params) { //用来向后台传请求参数,有queryParams就不用data:
                    $.extend(params, {
                        "logState": "配车中"
                    }); //searchParams返回的是参数格式  return {N_id:abc}
                    return params;
                },
                url: '/carscheme/searchCarS', //请求接口
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
                // columns.push(commonrow);
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
            $("#searchCPBtn").click(function (e) {
                e.preventDefault();
                // console.log(searchParams());
                $table.bootstrapTable('refresh', {
                    url: '/carscheme/searchCarS',
                    query: searchParams()
                });
            });

            function searchParams() {
                var sendParams = {},
                    selectData = $("#searchCPForm").find(':input');
                $.each(selectData, function (index, val) {
                    /* iterate through array or object */
                    sendParams[$(this).attr("name")] = $.trim($(this).val());
                });
                return sendParams;
            }

            function showModal() {
                $modal.modal("show");
            }

            /*关闭重置表单*/
            $modal.on('hide.bs.modal', function () {
                $("#resetBtn").click();
                $addForm.find(':input').val("");
                $('.selectAskPur').selectpicker('val', []);
            });
            // 点击配车按钮
            $("#insertCPBtn").on('click', function (event) {
                event.preventDefault();
                event.stopPropagation();
                /* Act on the event */
                $("#myModalLabel").text("配车计划");
                var selectIds = "",
                    selectNames = "",
                    selectUser = "",
                    allWeight = 0,
                    allVolume = 0,
                    allNum = 0;
                var selectTable = $table.bootstrapTable("getSelections");
                var p = $province.val() == null ? false : true,
                    c = $city.val() == null ? false : true,
                    a = $area.val() == null ? false : true;

                var myflag = false;
                var numAttr = "";

                if (!p || !c || !a) {
                    $.dialog("请先选择接收地址进行查询");
                    myflag = true;
                    resetData();
                    return false;
                } else {
                    if (selectTable != null && selectTable.length > 0) {
                        $.each(selectTable, function (index, val) {
                            /* iterate through array or object */

                            if (val.receiptProvinceid != $province.val() || val.receiptCityid != $city.val() || val.receiptAreaid != $area.val()) {
                                $.dialog("请先选择接收地址进行查询");
                                myflag = true;
                                resetData();
                                return false;
                            } else if (val.logState == "准备出发") {
                                $.dialog("物流已经配车过,请先筛选");
                                myflag = true;
                                resetData();
                                return false;
                            }
                            if (!myflag) {
                                for (var i = 0; i < val.goodsNumber; i++) {
                                    numAttr += val.goodsPerweight + ",";
                                }
                                selectIds += val.orderId + ",";
                                selectNames += val.goodsName + ",";
                                allWeight += val.goodsNumber * val.goodsPerweight;
                                allVolume += val.goodsVolume;
                                allNum += val.goodsNumber;
                                selectUser += val.receiptName + ",";
                            }
                        });
                    } else {
                        $.dialog("请先选择数据");
                        myflag = true;
                        resetData();
                        return false;
                    }
                }

                function resetData() {
                    selectIds = "",
                        selectNames = "",
                        selectUser = "",
                        allWeight = 0,
                        allVolume = 0,
                        allNum = 0;
                }
                selectIds = selectIds.substring(0, selectIds.length - 1);
                selectNames = selectNames.substring(0, selectNames.length - 1);
                selectUser = selectUser.substring(0, selectUser.length - 1);
                numAttr = numAttr.substring(0, numAttr.length - 1);
                $addForm.find(':input[name="orderNames"]').val(selectNames);
                $addForm.find(':input[name="orderIds"]').val(selectIds);
                $addForm.find(':input[name="allNumber"]').val(allNum);
                $addForm.find(':input[name="allWeight"]').val(allWeight);
                $addForm.find(':input[name="allVolume"]').val(allVolume);
                $addForm.find(':input[name="carplanPerson"]').val(username);
                $addForm.find(':input[name="receiptName"]').val(selectUser);
                $addForm.find(':input[name="carplanDept"]').val("运输部");
                $addForm.find(':input[name="provinceid"]').val($province.val());
                $addForm.find(':input[name="cityid"]').val($city.val());
                $addForm.find(':input[name="areaid"]').val($area.val());

                if (!myflag) {
                    showModal();
                }
                $("#calculateBtn").click(function (event) {
                    event.preventDefault();
                    event.stopPropagation();
                    var cartype = $("#selectCarT").val();

                    if (cartype != null && cartype != "") {
                        // var carNum = Math.ceil(allWeight / cartype);
                        // $addForm.find(':input[name="carnNum"]').val(Math.ceil(carNum));
                        Lprapm.Ajax.request({
                            url: '/carscheme/caculateCar',
                            data: {
                                "numAttr": numAttr,
                                "carType": cartype
                            },
                            success: function (response) {
                                if (response.success) {
                                    $addForm.find(':input[name="carnNum"]').val(Math.ceil(response.data));
                                } else {
                                    $.dialog(response.messages);
                                }
                            }
                        });

                    } else {
                        $.dialog("请选择车辆类型");
                    }
                });
            });

            $("#submitBtn").click(function (event) {
                /* 点击提交按钮 */
                if ($addForm.find(':input[name="carnNum"]').val() == "") {
                    $.dialog("请先计算车辆数目");
                } else {
                    submitData();
                }
            });

            function submitData() {
                var formData = {};
                formData = $addForm.serializeArray();
                Lprapm.Ajax.request({
                    url: '/carscheme/updateCarS',
                    data: formData,
                    success: function (response) {
                        if (response.success) {
                            $table.bootstrapTable("refresh");
                            $modal.modal('hide');
                            window.location.reload();
                        } else {
                            $.dialog(response.messages);
                        }
                    }
                });
            }

        }

        return {
            "carPlan": carPlan
        }
    })