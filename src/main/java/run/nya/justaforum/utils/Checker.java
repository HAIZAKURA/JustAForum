package run.nya.justaforum.utils;

import javax.servlet.http.HttpSession;

public class Checker {

    public static boolean isAdmin(HttpSession session) {
        String uacce = session.getAttribute("uacce").toString();
        if (uacce.equals("1")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isUser(HttpSession session) {
        String uacce = session.getAttribute("uacce").toString();
        if (uacce.equals("0")) {
            return true;
        } else {
            return false;
        }
    }

}
