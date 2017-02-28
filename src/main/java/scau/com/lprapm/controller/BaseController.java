package scau.com.lprapm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.util.WebUtils;
import scau.com.lprapm.common.Constant;
import scau.com.lprapm.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by 钟锐锋 on 2016/12/13.
 */

public class BaseController {
    @Autowired
    private HttpServletRequest request;

    public User getUserCurrent(){
        Object obj= WebUtils.getSessionAttribute(this.request, Constant.CURRENR_USER);
        return (User)obj;
    }
//    只能获取1条数据
    public Map<String, Object> getParamMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        LinkedHashMap<String, String[]> map = new LinkedHashMap<>(request.getParameterMap());
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            result.put(key, values[0]);
        }
        return result;
    }
    /**
     * 当有日期传进来时，如果要用到entity，则需要进行日期转换，否则报错
     * @param binder
     * 另一种简单的方法是：
     * 在entity中
     * @DateTimeFormat(pattern = "yyyy-MM-dd")
     *private Date createTime;
     */
    @InitBinder
    public void InitBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
    }

}
