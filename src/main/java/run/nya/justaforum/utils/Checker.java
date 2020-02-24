package run.nya.justaforum.utils;

import javax.servlet.http.HttpSession;

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

}
