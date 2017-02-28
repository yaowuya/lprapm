/**
 * 重新封装bootstrapselect,
 * 只需要一次从后台加载数据，
 * 可用于一个页面有一个bootstrapselect下拉框
 */
define(['ajaxPackage', 'bootstrapSelect'],
    function(ajaxPakage) {
        var info = [],
            $options = "",
            options = {},
            baseOptions = { //默认参数
                isSearch: true, //是否显示搜索框
                multiple: true, //是否多选
                width: '150px', //长度
                actionBox: true, //是否展示全选、取消按钮
                title: '请选择', //默认提示
                dataSize: 5, //最多显示个数，数据多时会有滚动条
                selectWith: 0, //展示供选择层的宽度
            };

        var setSelectList = {
                option: function($select, selectOption, Url, name, code, selected) {
                    // selected是一个数组
                    var namekey="",codekey="";
                    namekey = name;
                    codekey = code;
                    selected = selected ? selected : []; //初始化的选项，为数组
                    if (Url != null && Url != "") {
                        $.extend(baseOptions, selectOption);
                        if (info.length == 0) {
                            ajaxPakage.Ajax.request({
                                url: Url,
                                async:false,
                                success: function(response) {
                                    var successData = response.data;
                                    info = response.data;
                                    $.each(successData, function(index, item) { //后台返回值
                                        options[item[codekey]] = "<option value='" + item[codekey] + "' data-subtext='" + item[codekey] + "'>" + item[namekey] + "</option>"
                                    });
                                    LoadInfoOption($select, selected);
                                    $select.selectpicker('val', selected);
                                }
                            });
                        } else {
                            LoadInfoOption($select, []);
                            $select.selectpicker('val', selected);
                        }
                        if (!baseOptions.multiple) { /*当为单选时，点击已选的取消选择*/
                            $select.prev().delegate('ul li', 'click', function() {
                                if ($(this).hasClass('active')) {
                                    $select.selectpicker('val',[]);
                                }
                            });
                        }
                    } else {
                        console.log("url不能为空");
                    }
                },
                select: function showSelect($select, selected) {
                    if (selected != undefined && selected.length > 0) {
                        $select.selectpicker('destroy');
                        LoadInfoOption($select, selected);
                        $select.selectpicker('val', selected);
                    } else {
                        $select.selectpicker('refresh');
                        $select.selectpicker('val', []);
                    }
                }
            }
            /*根据参数配置修改下拉框的配置，加载下拉选项*/
        function LoadInfoOption($select, selected) { //可以进行其他操作
            setOptions($select);
            var topHtml = "";
            var aa = true;
            $options = $.map(options, function(value, index) {
                if ($.inArray(index, selected) != -1) {
                    topHtml += value;
                } else {
                    return value;
                }
            }).join("");
            $options = topHtml + $options;
            $select.empty().append($options).selectpicker("refresh");
            /*控制下拉框.bs-searchbox的宽度 */
            $("div.my-search-input").delegate('button.dropdown-toggle', 'click', function(event) {
                var $selectItem = $(this).parent().find('div.dropdown-menu');
                if (baseOptions.selectWith) {
                    $selectItem.css('width', baseOptions.selectWith);
                    $selectItem.css('left', '0px');
                    $selectItem.css('right', 'auto');
                    $selectItem.find('input.form-control').css('padding', '0px 0px');
                } else {
                    $selectItem.css('width', $(this).innerWidth());
                    $selectItem.find('input.form-control').css('padding', '0px 0px');
                }
            });
        }

        function setOptions($select) {
            $select.addClass('my-search-input');
            if (baseOptions.isSearch) {
                $select.attr("data-live-search", "true");
            } else {
                $select.removeAttr("data-live-search");
            }
            if (baseOptions.multiple) {
                $select.attr('multiple', 'true');
            } else {
                $select.removeAttr('multiple');
            }
            if (baseOptions.actionBox) {
                $select.attr('data-actions-box', 'true');
            } else {
                $select.removeAttr('data-actions-box');
            }

            $select.attr('data-width', baseOptions.width);
            $select.attr('title', baseOptions.title);
            $select.attr('data-size', baseOptions.dataSize);
        }

        return {
            "selectList": setSelectList
        }
    });