package run.nya.justaforum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import run.nya.justaforum.model.bean.User;
import run.nya.justaforum.model.dao.UserDAO;
import run.nya.justaforum.utils.MD5;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired(required = false)
    private UserDAO userDAO;

    @GetMapping(value = "")
    public String index() {
        return "Hello World!";
    }

    @GetMapping(value = "userLogin")
    public ModelAndView userLogin() {
        return new ModelAndView("userLogin");
    }

    @PostMapping(value = "userLogin.do")
    public Object userLogin(String uname, String upass) {
//        System.out.println("uname = " + uname + " & upass = " + upass);
        Map<String, String> res = new HashMap<>();
        if (StringUtils.isEmpty(uname)) {
            return "username can not be empty";
        } else if (StringUtils.isEmpty(upass)) {
            return "password can not be empty";
        }
        String procPass = uname + upass;
        String finalUpass = MD5.getMD5(procPass);
        User user = userDAO.userFind(uname, finalUpass);
        if (user != null) {
            res.put("uname", uname);
            res.put("status", "success");
//            return user;
            return res;
        } else {
            res.put("uname", uname);
            res.put("status", "fail");
//            return "username or password is wrong";
            return res;
        }
    }

    @PostMapping(value = "adminLogin.do")
    public Object adminLogin(String uname, String upass) {
        Map<String, String> res = new HashMap<>();
        if (StringUtils.isEmpty(uname)) {
            return "username can not be empty";
        } else if (StringUtils.isEmpty(upass)) {
            return "password can not be empty";
        }
        String procPass = uname + upass;
        String finalUpass = MD5.getMD5(procPass);
        User user = userDAO.adminFind(uname, finalUpass);
        if (user != null) {
            res.put("uname", uname);
            res.put("status", "success");
            return res;
        } else {
            res.put("uname", uname);
            res.put("status", "fail");
            return res;
        }
    }

}
