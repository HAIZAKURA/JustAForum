package run.nya.justaforum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import run.nya.justaforum.model.bean.User;
import run.nya.justaforum.model.dao.UserDAO;
import run.nya.justaforum.utils.Checker;
import run.nya.justaforum.utils.Tool;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@SessionAttributes(value = {"uid", "uname", "uacce"}, types = {Integer.class, String.class, Integer.class})
public class UserController {

    /**
     * status error 权限不足
     * status empty 参数为空
     * status success 操作成功
     * status fail 操作失败
     */

    @Autowired(required = false)
    private UserDAO userDAO;

    @GetMapping(value = "")
    public String index() {
        return "Hello World!";
    }

    @GetMapping(value = "/login")
    public ModelAndView userLogin() {
        return new ModelAndView("userLogin");
    }

    /**
     *
     * @name   userLogin
     * @mark   用户登录
     * @access All
     * @URL    /commApi/login.do
     * @method POST
     * @param  uname
     * @param  upass
     * @param  modelMap
     * @param  session
     * @return
     *
     */
    @PostMapping(value = "/commApi/login.do")
    public Object userLogin(String uname, String upass, ModelMap modelMap, HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        res.put("uname", uname);
        if (StringUtils.isEmpty(uname) || StringUtils.isEmpty(upass)) {
            res.put("status", "empty");
        } else {
            String procPass = uname + upass;
            String finalUpass = Tool.getMD5(procPass);
            User user = userDAO.userFind(uname, finalUpass);
            if (user != null) {
                if (user.getUstat() == 1) {
                    res.put("status", "fail");
                } else {
                    modelMap.addAttribute("uid", user.getUid());
                    modelMap.addAttribute("uname", user.getUname());
                    modelMap.addAttribute("uacce", user.getUacce());
                    res.put("uid", user.getUid());
                    res.put("uacce", user.getUacce());
                    res.put("status", "success");
                }
            } else {
                res.put("status", "fail");
            }
        }
        return res;
    }

    /**
     *
     * @name   adminLogin
     * @mark   管理员登录
     * @access Admin
     * @URL    /adminApi/login.do
     * @method POST
     * @param  uname
     * @param  upass
     * @param  modelMap
     * @param  session
     * @return
     *
     */
    @PostMapping(value = "/adminApi/login.do")
    public Object adminLogin(String uname, String upass, ModelMap modelMap, HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        res.put("uname", uname);
        if (StringUtils.isEmpty(uname) || StringUtils.isEmpty(upass)) {
            res.put("status", "empty");
        } else {
            String procPass = uname + upass;
            String finalUpass = Tool.getMD5(procPass);
            User user = userDAO.adminFind(uname, finalUpass);
            if (user != null) {
                modelMap.addAttribute("uid", user.getUid());
                modelMap.addAttribute("uname", user.getUname());
                modelMap.addAttribute("uacce", user.getUacce());
                res.put("uid", user.getUid());
                res.put("uacce", user.getUacce());
                res.put("status", "success");
            } else {
                res.put("status", "fail");
            }
        }
        return res;
    }

    /**
     *
     * @name   logout
     * @mark   登出
     * @access All
     * @URL    /logout.do
     * @method GET
     * @param  sessionStatus
     * @param  session
     * @return
     *
     */
    @RequestMapping(value = "/logout.do")
    public Object userLogout(SessionStatus sessionStatus, HttpSession session){
        Map<String, Object> res = new HashMap<>();
        String uid = session.getAttribute("uid").toString();
        res.put("uid", uid);
        try{
            sessionStatus.setComplete();
            res.put("status", "success");
        } catch (Exception e) {
//            e.printStackTrace();
            res.put("status", "fail");
        }
        return res;
    }

    /**
     *
     * @name   isLogin
     * @mark   用户登录验证
     * @access All
     * @URL    /commApi/isLogin.do
     * @method GET
     * @param  session
     * @return
     *
     */
    @GetMapping(value = "/commApi/isLogin.do")
    public Object isLogin(HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        String uid = session.getAttribute("uid").toString();
        String uname = session.getAttribute("uname").toString();
        String uacce = session.getAttribute("uacce").toString();
        if (StringUtils.isEmpty(uid) || StringUtils.isEmpty(uname) || StringUtils.isEmpty(uacce)) {
            res.put("status", "fail");
        } else {
            if (Checker.isUser(session)) {
                res.put("status", "success");
            } else {
                res.put("status", "fail");
            }
        }
        return res;
    }

    /**
     *
     * @name   isAdminLogin
     * @mark   管理员登录验证
     * @access All
     * @URL    /commApi/isAdminLogin.do
     * @method GET
     * @param  session
     * @return
     *
     */
    @GetMapping(value = "/commApi/isAdminLogin.do")
    public Object isAdminLogin(HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        String uid = session.getAttribute("uid").toString();
        String uname = session.getAttribute("uname").toString();
        String uacce = session.getAttribute("uacce").toString();
        if (StringUtils.isEmpty(uid) || StringUtils.isEmpty(uname) || StringUtils.isEmpty(uacce)) {
            res.put("status", "fail");
        } else {
            if (Checker.isAdmin(session)) {
                res.put("status", "success");
            } else {
                res.put("status", "fail");
            }
        }
        return res;
    }

    /**
     *
     * @name   getUser
     * @mark   获取用户信息 - 通过用户名
     * @access All
     * @URL    /commApi/getUser.do
     * @method GET
     * @param  uname
     * @return
     *
     */
    @GetMapping(value = "/commApi/getUser.do")
    public Object getUser(String uname) {
        Map<String, Object> res = new HashMap<>();
        res.put("uname", uname);
        if (StringUtils.isEmpty(uname)) {
            res.put("status", "empty");
        } else {
            User user = userDAO.getUser(uname);
            if (user != null) {
                res.put("status", "success");
                res.put("uid", user.getUid());
                res.put("umail", user.getUmail());
                res.put("uacce", user.getUacce());
                res.put("upoit", user.getUpoit());
                res.put("ustat", user.getUstat());
            } else {
                res.put("status", "fail");
            }
        }
        return res;
    }

    /**
     *
     * @name   getUserById
     * @mark   获取用户信息 - 通过用户ID
     * @access All
     * @URL    /commApi/getUserById.do
     * @method GET
     * @param  uid
     * @return
     *
     */
    @GetMapping(value = "/commApi/getUserById.do")
    public Object getUserById(Integer uid) {
        Map<String, Object> res = new HashMap<>();
        res.put("uid", uid);
        if (uid <= 0) {
            res.put("status", "empty");
        } else {
            User user = userDAO.getUserById(uid);
            if (user != null) {
                res.put("status", "success");
                res.put("uname", user.getUname());
                res.put("umail", user.getUmail());
                res.put("uacce", user.getUacce());
                res.put("upoit", user.getUpoit());
                res.put("ustat", user.getUstat());
            } else {
                res.put("status", "fail");
            }
        }
        return res;
    }

    /**
     *
     * @name   getAllUser
     * @mark   获取所有用户信息
     * @access All
     * @URL    /commApi/getAllUser.do
     * @method GET
     * @return
     *
     */
    @GetMapping(value = "/commApi/getAllUser.do")
    public Object getAllUser() {
        List<Map<String, Object>> res = new ArrayList<>();
        List<User> allUser = userDAO.getAllUser();
        for (User user : allUser) {
            Map<String, Object> map = new HashMap<>();
            map.put("uid", user.getUid());
            map.put("uname",user.getUname());
            map.put("umail", user.getUmail());
            map.put("uacce", user.getUacce());
            map.put("upoit", user.getUpoit());
            map.put("ustat", user.getUstat());
            res.add(map);
        }
        return res;
    }

    /**
     *
     * @name   delUser
     * @mark   删除用户
     * @access Admin
     * @URL    /adminApi/delUser.do
     * @method GET
     * @param  uid
     * @param  session
     * @return
     *
     */
    @GetMapping(value = "/adminApi/delUser.do")
    public Object delUser(Integer uid, HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        res.put("uid", uid);
        if (Checker.isAdmin(session)) {
            if (uid <= 0) {
                res.put("status", "empty");
            } else {
                try {
                    Integer backU = userDAO.delUser(uid);
                    res.put("status", "success");
                } catch (Exception e) {
//                e.printStackTrace();
                    res.put("status", "fail");
                }
            }
        } else {
            res.put("status", "error");
        }
        return res;
    }

    /**
     *
     * @name   banUser
     * @mark   封禁用户
     * @access Admin
     * @URL    /adminApi/banUser.do
     * @method GET
     * @param  uid
     * @param  session
     * @return
     *
     */
    @GetMapping(value = "/adminApi/banUser.do")
    public Object banUser(Integer uid, HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        res.put("uid", uid);
        if (Checker.isAdmin(session)) {
            if (uid <= 0) {
                res.put("status", "empty");
            } else {
                try {
                    Integer backU = userDAO.banUser(uid);
                    res.put("status", "success");
                } catch (Exception e) {
//                    e.printStackTrace();
                    res.put("status", "fail");
                }
            }
        } else {
            res.put("status", "error");
        }
        return res;
    }

    /**
     *
     * @name   debUser
     * @mark   解禁用户
     * @access Admin
     * @URL    /adminApi/debUser.do
     * @method GET
     * @param  uid
     * @param  session
     * @return
     *
     */
    @GetMapping(value = "/adminApi/debUser.do")
    public Object debUser(Integer uid, HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        res.put("uid", uid);
        if (Checker.isAdmin(session)) {
            if (uid <= 0) {
                res.put("status", "empty");
            } else {
                try {
                    Integer backU = userDAO.debUser(uid);
                    res.put("status", "success");
                } catch (Exception e) {
//                    e.printStackTrace();
                    res.put("status", "fail");
                }
            }
        } else {
            res.put("status", "error");
        }
        return res;
    }

    /**
     *
     * @name   addUser
     * @mark   新增用户
     * @access All
     * @URL    /commApi/addUser.do
     * @method POST
     * @param  uname
     * @param  upass
     * @param  umail
     * @return
     *
     */
    @PostMapping(value = "/commApi/addUser.do")
    public Object addUser(String uname, String upass, String umail) {
        Map<String, Object> res = new HashMap<>();
        res.put("uname", uname);
        if (StringUtils.isEmpty(uname) || StringUtils.isEmpty(upass) || StringUtils.isEmpty(umail)) {
            res.put("status", "empty");
        } else {
            try {
                String procPass = uname + upass;
                String finalUpass = Tool.getMD5(procPass);
                Integer backU = userDAO.addUser(uname, finalUpass, umail);
                res.put("status", "success");
            } catch (Exception e) {
//            e.printStackTrace();
                res.put("status", "fail");
            }
        }
        return res;
    }

    /**
     *
     * @name   modUserPass
     * @mark   修改密码
     * @access User
     * @URL    /userApi/modUserPass.do
     * @method POST
     * @param  oldpass
     * @param  newpass
     * @param  session
     * @return
     *
     */
    @PostMapping(value = "/userApi/modUserPass.do")
    public Object modUserPass(String oldpass, String newpass, HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        Integer uid = Checker.getUidBySession(session);
        String uname = Checker.getUnameBySession(session);
        res.put("uid", uid);
        if (Checker.isUser(session)) {
            if (StringUtils.isEmpty(oldpass) || StringUtils.isEmpty(newpass)) {
                res.put("status", "empty");
            } else {
                String procOldPass = uname + oldpass;
                String finalOldPass = Tool.getMD5(procOldPass);
                String procNewPass = uname + newpass;
                String finalNewPass = Tool.getMD5(procNewPass);
                try {
                    Integer backU = userDAO.modUserPass(uid, finalOldPass, finalNewPass);
                    res.put("status", "success");
                } catch (Exception e) {
//                e.printStackTrace();
                    res.put("status", "fail");
                }
            }
        } else {
            res.put("status", "error");
        }
        return res;
    }

    /**
     *
     * @name   modUserMail
     * @mark   修改邮箱
     * @access User
     * @URL    /userApi/modUserMail.do
     * @method POST
     * @param  upass
     * @param  umail
     * @param  session
     * @return
     *
     */
    @PostMapping(value = "/userApi/modUserMail.do")
    public Object modUserMail(String upass, String umail, HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        Integer uid = Checker.getUidBySession(session);
        String uname = Checker.getUnameBySession(session);
        res.put("uid", uid);
        if (Checker.isUser(session)) {
            if (StringUtils.isEmpty(umail)) {
                res.put("status", "empty");
            } else {
                String procPass = uname + upass;
                String finalPass = Tool.getMD5(procPass);
                try {
                    Integer backU = userDAO.modUserMail(uid, finalPass, umail);
                    res.put("status", "success");
                } catch (Exception e) {
//                e.printStackTrace();
                    res.put("status", "fail");
                }
            }
        } else {
            res.put("status", "error");
        }
        return res;
    }

    /**
     *
     * @name   modUserById
     * @mark   修改用户信息 - 管理员侧
     * @access Admin
     * @URL    /adminApi/modUserById.do
     * @method POST
     * @param  uid
     * @param  uname
     * @param  umail
     * @param  uacce
     * @param  ustat
     * @param  session
     * @return
     *
     */
    @PostMapping(value = "/adminApi/modUserById.do")
    public Object modUserById(Integer uid, String uname, String umail, Integer uacce, Integer ustat, HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        res.put("uid", uid);
        if (Checker.isAdmin(session)) {
            if (uid <= 0) {
                res.put("status", "empty");
            } else {
                try {
                    Integer backU = userDAO.modUserById(uid, uname, umail, uacce, ustat);
                    res.put("status", "success");
                } catch (Exception e) {
//                e.printStackTrace();
                    res.put("status", "fail");
                }
            }
        } else {
            res.put("status", "error");
        }
        return res;
    }

}
