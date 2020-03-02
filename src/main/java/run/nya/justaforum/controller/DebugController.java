package run.nya.justaforum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import run.nya.justaforum.model.bean.User;
import run.nya.justaforum.model.dao.NodeDAO;
import run.nya.justaforum.model.dao.TagDAO;
import run.nya.justaforum.model.dao.TopicDAO;
import run.nya.justaforum.model.dao.UserDAO;
import run.nya.justaforum.utils.Checker;

import javax.servlet.http.HttpSession;

@RestController
public class DebugController {

    @Autowired(required = false)
    private UserDAO userDAO;
    private NodeDAO nodeDAO;
    private TagDAO tagDAO;
    private TopicDAO topicDAO;

    //    session验证 测试用
    @GetMapping(value = "/debug/check.do")
    public Object check(HttpSession session) {
//        Map<String, Object> res = new HashMap<>();
//        res.put("uid", session.getAttribute("uid"));
//        res.put("uname", session.getAttribute("uname"));
//        res.put("uacce", session.getAttribute("uacce"));
//        return res;
        boolean res = Checker.isAdmin(session);
        return res;
    }

    @GetMapping(value = "/debug/addTopicPoit.do")
    public User addTopicPoit(Integer uid) {
        Integer back = userDAO.addUserTopicPoit(uid);
        User user = userDAO.getUserById(uid);
        return user;
    }

}
