package scau.com.lprapm.common;

import java.util.UUID;

/**
 * 常量
 * Created by 钟锐锋 on 2017/1/11.
 */
public class Constant {

//    session
    public static final String CURRENR_USER="current_user";
    public static final String CURRENR_ADDR = "current_addr";

    /**
     * 当主键为varchar(32)时，可用该函数产生主键
     * @return
     */
    public static String generateID(){
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

}