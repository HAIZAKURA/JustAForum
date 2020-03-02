package run.nya.justaforum.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Checker {

    /**
     *
     * @name  isAdmin
     * @mark  判断用户是否危管理员
     * @param session
     * @return
     *
     */
    public static boolean isAdmin(HttpSession session) {
        String uacce = session.getAttribute("uacce").toString();
        if (uacce.equals("1")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @name  isUser
     * @mark  判断用户是否危普通用户
     * @param session
     * @return
     *
     */
    public static boolean isUser(HttpSession session) {
        String uacce = session.getAttribute("uacce").toString();
        if (uacce.equals("0")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @name  getUidBySession
     * @mark  从Session中获取用户ID
     * @param session
     * @return
     */
    public static Integer getUidBySession(HttpSession session) {
        Integer uid;
        try {
            Integer sessionUid = Integer.valueOf(session.getAttribute("uid").toString());
            uid = sessionUid;
        } catch (NumberFormatException e) {
//            e.printStackTrace()
            uid = 0;
        }
        return uid;
    }

    /**
     *
     * @name  getUnameBySession
     * @mark  从Session中获取用户名
     * @param session
     * @return
     */
    public static String getUnameBySession(HttpSession session) {
        String uname;
        try {
            String sessionUname = String.valueOf(session.getAttribute("uname"));
            uname = sessionUname;
        } catch (Exception e) {
//            e.printStackTrace();
            uname = null;
        }
        return uname;
    }

}
