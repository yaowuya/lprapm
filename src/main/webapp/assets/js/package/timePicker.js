/**
 * 封装时间选择下拉框
 */
define(['moment', 'datepicker'], function(moment) {
    /**
     * [picker 两个参数为id #XXX]
     * @param  {[type]} time1 [第一个参数不能为空] 
     * @param  {[type]} time2 [第二个参数可以为null]
     * @return {[type]}       [description]
     */
    var picker = function(time1, time2) {
        if (time1 != null) {
            if (time2 != null) {
                var picker1 = $(time1).datetimepicker({
                    format: 'YYYY-MM-DD',
                    locale: moment.locale('zh-cn'),
                    dayViewHeaderFormat: 'YYYY MM',
                    stepping: '2',
                    showTodayButton: true,
                    useCurrent: false,
                });
                var picker2 = $(time2).datetimepicker({
                    format: 'YYYY-MM-DD',
                    locale: moment.locale('zh-cn'),
                    dayViewHeaderFormat: 'YYYY MM',
                    stepping: '2',
                    showTodayButton: true,
                    useCurrent: false,
                });
                //动态设置最小值  
                picker1.on('dp.change', function(e) {
                    picker2.data('DateTimePicker').minDate(e.date);
                });
                //动态设置最大值  
                picker2.on('dp.change', function(e) {
                    picker1.data('DateTimePicker').maxDate(e.date);
                });
            } else {
                var picker = $(time1).datetimepicker({
                    format: 'YYYY-MM-DD',
                    locale: moment.locale('zh-cn'),
                    dayViewHeaderFormat: 'YYYY MM',
                    stepping: '2',
                    showTodayButton: true,
                    useCurrent: false,
                });
                picker.on('dp.change', function(e) {
                    picker.data('DateTimePicker').hide();
                });
            }
        } else {
            console.log("第一个参数不能为空");
        }
    }

    return{
        "picker":picker
    }
})