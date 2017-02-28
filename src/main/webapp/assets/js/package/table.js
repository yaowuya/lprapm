// 重新封装bootstrap-table
define(['bootstrapTable','bootstrapZhCN'],function(){
    // BootstrapTable全局设置
    $.extend(jQuery.fn.bootstrapTable.defaults,{
        locale:'zh-CN',//中文
        cache:'false',//设置为 true 禁用 AJAX 数据缓存
        search:false,//是否打开搜索框
        pagination:true,//启用分页
        sidePagination:"client",//设置在哪里进行分页，可选值为 'client'或者 'server'。设置'server'时，必须设置服务器数据地址（url）或者重写ajax方法,而且后台必须传回total
        pageNumber:1,//如果启用了分页，首页页码
        pageSize:10,//如果设置了分页，页面数据条数
        pageList:[10, 25, 50, 100, "All"],//如果设置了分页，设置可供选择的页面数据条数。设置为All则显示所有记录。
        dataField:'data',//json返回的数据的名叫data{data:}
        method:'post',
        contentType:'application/x-www-form-urlencoded;charset=utf-8',//发送到服务器的数据编码类型
        queryParamsType:'nolimit',//请求参数名称全部使用全称呼
        paginationPreText:'上一页',//  指定分页条中上一页按钮的图标或文字
        paginationNextText:'下一页',//指定分页条中下一页按钮的图标或文字
        rowStyle:function(row,index){
            //这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
            var strclass="";
            if(index%2==0){
                strclass='table-even-row-style';
            }else{
                strclass='table-uneven-row-style';
            }
            return {classes:strclass};//class可以自己定义在css中
        },
        // silentSort:false//服务器端排序，设置为false将在点击分页按钮时，自动记住排序项。仅在 sidePagination设置为 server时生效.
    });
    
});