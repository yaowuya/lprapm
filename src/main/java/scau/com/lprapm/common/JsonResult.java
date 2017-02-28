package scau.com.lprapm.common;

import java.util.Collection;

/**
 * 将集合转换为json
 * 输出的json中{"total":1,"data":[{"aa":"aa","bb":"bb"},"success":true,"messages":""]}
 * data位集合
 * Created by zhongrf on 2016/12/13.
 */
public class JsonResult<T>{
    private int total=0;
    private boolean success=false;
    private String messages="";
    private T data;

    public JsonResult(int total, boolean success, String messages, T data) {
        this.total = total;
        this.success = success;
        this.messages = messages;
        this.data = data;
    }

    public JsonResult(boolean success, String messages, T data) {
        this.total=0;
        this.success = success;
        this.messages = messages;
        this.data = data;
    }

    public JsonResult(boolean success, String messages) {
        this.total=0;
        this.success = success;
        this.messages = messages;
        this.data=null;
    }

    public JsonResult(int total, boolean success, T data) {
        this.total = total;
        this.success = success;
        this.messages="";
        this.data = data;
    }

    public JsonResult(int total, T data) {
        this.total = total;
        this.success=true;
        this.data = data;
    }

    public JsonResult(boolean success) {
        this.success = success;
        this.messages="";
    }

    public JsonResult(T data) {
        this.success=true;
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "total=" + total +
                ", success=" + success +
                ", messages='" + messages + '\'' +
                ", data=" + data +
                '}';
    }
}
