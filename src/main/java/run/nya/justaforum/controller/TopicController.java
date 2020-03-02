package run.nya.justaforum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import run.nya.justaforum.model.dao.TagDAO;
import run.nya.justaforum.model.dao.TopicDAO;
import run.nya.justaforum.model.dao.UserDAO;
import run.nya.justaforum.utils.Checker;
import run.nya.justaforum.utils.Tool;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TopicController {

    /**
     * status error 权限不足
     * status empty 参数为空
     * status success 操作成功
     * status fail 操作失败
     */

    @Autowired(required = false)
    private TopicDAO topicDAO;
    private TagDAO tagDAO;
    private UserDAO userDAO;

    /**
     *
     * @name   addTopic
     * @mark   新增话题
     * @access User & Admin
     * @URL    /commApi/addTopic.do
     * @method POST
     * @param  tname
     * @param  tcont
     * @param  gid
     * @param  session
     * @return
     *
     */
    @PostMapping(value = "/commApi/addTopic.do")
    public Object addTopic(String tname, String tcont, Integer gid, HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        res.put("tname", tname);
        if (Checker.isUser(session) || Checker.isAdmin(session)) {
            if (StringUtils.isEmpty(tname) || StringUtils.isEmpty(tcont)) {
                res.put("status", "empty");
            } else {
                try {
                    String tdate = Tool.getNowTime();
                    Integer uid = Checker.getUidBySession(session);
                    Integer nid = tagDAO.getTag(gid).getNid();
                    Integer backT = topicDAO.addTopic(tname, tcont, tdate, uid, nid, gid);
                    Integer backU = userDAO.addUserTopicPoit(uid);
                    res.put("status", "success");
                } catch (NumberFormatException e) {
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
     * @name   delTopic
     * @mark   删除话题
     * @access Admin
     * @URL    /adminApi/delTopic.do
     * @method GET
     * @param  tid
     * @param  session
     * @return
     *
     */
    @GetMapping(value = "/adminApi/delTopic.do")
    public Object delTopic(Integer tid, HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        res.put("tid", tid);
        if (Checker.isAdmin(session)) {
            if (tid == 0) {
                res.put("status", "empty");
            } else {
                try {
                    Integer backT = topicDAO.delTopic(tid);
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

}
