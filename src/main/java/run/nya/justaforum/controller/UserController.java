package run.nya.justaforum.controller;

import com.sun.deploy.security.WSeedGenerator;
import jdk.internal.org.objectweb.asm.tree.InnerClassNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import run.nya.justaforum.model.bean.User;
import run.nya.justaforum.model.dao.UserDAO;
import run.nya.justaforum.utils.MD5;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.PastOrPresent;
import java.util.*;

@RestController
@SessionAttributes(value = {"uid", "uname", "uacce"}, types = {Integer.class, String.class, Integer.class})
public class UserController {

//    status error 权限不足
//    status empty 参数为空
//    status success 操作成功
//    status fail 操作失败

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

//    用户登录
    @PostMapping(value = "/userApi/login.do")
    public Object userLogin(String uname, String upass, ModelMap modelMap, HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        res.put("uname", uname);
        if (StringUtils.isEmpty(uname) || StringUtils.isEmpty(upass)) {
            res.put("status", "empty");
        } else {
            String procPass = uname + upass;
            String finalUpass = MD5.getMD5(procPass);
            User user = userDAO.userFind(uname, finalUpass);
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

//    管理员登录
    @PostMapping(value = "/adminApi/login.do")
    public Object adminLogin(String uname, String upass) {
        Map<String, Object> res = new HashMap<>();
        res.put("uname", uname);
        if (StringUtils.isEmpty(uname) || StringUtils.isEmpty(upass)) {
            res.put("status", "empty");
        } else {
            String procPass = uname + upass;
            String finalUpass = MD5.getMD5(procPass);
            User user = userDAO.adminFind(uname, finalUpass);
            if (user != null) {
                res.put("status", "success");
            } else {
                res.put("status", "fail");
            }
        }
        return res;
    }

//    用户登出
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

//    session验证 测试用
    @GetMapping(value = "/check")
    public Object check(HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        res.put("uid", session.getAttribute("uid"));
        res.put("uname", session.getAttribute("uname"));
        res.put("uacce", session.getAttribute("uacce"));
        return res;
    }

//    获取用户信息 通过用户名
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

//    获取用户信息 通过用户ID
    @GetMapping(value = "/commApi/getUserById.do")
    public Object getUserById(Integer uid, HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        res.put("uid", uid);
        if (uid == 0) {
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

//    获取所有用户信息
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

//    删除用户 仅管理员操作
    @GetMapping(value = "/adminApi/delUser.do")
    public Object delUser(String uname, HttpSession session) {
        String curUacce = session.getAttribute("uacce").toString();
        Map<String, Object> res = new HashMap<>();
        res.put("uname", uname);
        if (!curUacce.equals("1")) {
            res.put("status", "error");
        } else {
            if (StringUtils.isEmpty(uname)) {
                res.put("status", "empty");
            } else {
                try {
                    Integer back = userDAO.delUser(uname);
                    res.put("status", "success");
                } catch (Exception e) {
//                e.printStackTrace();
                    res.put("status", "fail");
                }
            }
        }
        return res;
    }

//    增加用户
    @PostMapping(value = "/commApi/addUser.do")
    public Object addUser(String uname, String upass, String umail) {
        Map<String, Object> res = new HashMap<>();
        res.put("uname", uname);
        try {
            String procPass = uname + upass;
            String finalUpass = MD5.getMD5(procPass);
            Integer back = userDAO.addUser(uname, finalUpass, umail);
            res.put("status", "success");
        } catch (Exception e) {
//            e.printStackTrace();
            res.put("status", "fail");
        }
        return res;
    }

//    修改用户密码 仅用户操作
    @PostMapping(value = "/userApi/modUserPass.do")
    public Object modUserPass(String uname, String oldpass, String newpass) {
        Map<String, Object> res = new HashMap<>();
        res.put("uname", uname);
        if (StringUtils.isEmpty(oldpass) || StringUtils.isEmpty(newpass)) {
            res.put("status", "empty");
        } else {
            String procOldPass = uname + oldpass;
            String finalOldPass = MD5.getMD5(procOldPass);
            String procNewPass = uname + newpass;
            String finalNewPass = MD5.getMD5(procNewPass);
            try {
                Integer back = userDAO.modUserPass(uname, finalOldPass, finalNewPass);
                res.put("status", "success");
            } catch (Exception e) {
//                e.printStackTrace();
                res.put("status", "fail");
            }
        }
        return res;
    }

//    修改用户 通过ID 仅管理员操作
    @PostMapping(value = "/adminApi/modUserById.do")
    public Object modUserById(Integer uid, String uname, String umail, Integer uacce, Integer ustat, HttpSession session) {
        String curUacce = session.getAttribute("uacce").toString();
        Map<String, Object> res = new HashMap<>();
        res.put("uid", uid);
        if (!curUacce.equals("1")) {
            res.put("status", "error");
        } else {
            if (uid == 0) {
                res.put("status", "empty");
            } else {
                try {
                    Integer back = userDAO.modUserById(uid, uname, umail, uacce, ustat);
                    res.put("status", "success");
                } catch (Exception e) {
//                e.printStackTrace();
                    res.put("status", "fail");
                }
            }
        }
        return res;
    }

}
