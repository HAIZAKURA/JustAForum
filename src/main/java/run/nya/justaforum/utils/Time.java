package run.nya.justaforum.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {

    /**
     *
     * @name getNowTime
     * @mark 获取当前时间
     * @return
     *
     */
    public static String getNowTime() {
        String res = null;
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        res = sdf.format(date);
        return res;
    }

}
