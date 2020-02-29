package run.nya.justaforum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import run.nya.justaforum.model.bean.Tag;
import run.nya.justaforum.model.dao.TagDAO;
import run.nya.justaforum.utils.Checker;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TagController {

    /**
     * status error 权限不足
     * status empty 参数为空
     * status success 操作成功
     * status fail 操作失败
     */

    @Autowired(required = false)
    private TagDAO tagDAO;

    /**
     *
     * @name   getAllTag
     * @mark   获取所有分类信息
     * @access All
     * @URL    /commApi/getAllTag.do
     * @method GET
     * @return
     *
     */
    @GetMapping(value = "/commApi/getAllTag.do")
    public Object getAllTag() {
        List<Map<String, Object>> res = new ArrayList<>();
        List<Tag> allTag = tagDAO.getAllTag();
        for (Tag tag : allTag) {
            Map<String, Object> map = new HashMap<>();
            map.put("gid", tag.getGid());
            map.put("gname", tag.getGanme());
            map.put("nid", tag.getNid());
            res.add(map);
        }
        return res;
    }

    /**
     *
     * @name   addTag
     * @mark   新增分类
     * @access Admin
     * @URL    /adminApi/addTag.do
     * @method POST
     * @param  gname
     * @param  nid
     * @param  session
     * @return
     *
     */
    @PostMapping(value = "/adminApi/addTag.do")
    public Object addTag(String gname, Integer nid, HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        res.put("gname", gname);
        if (Checker.isAdmin(session)) {
            if (StringUtils.isEmpty(gname)) {
                res.put("status", "empty");
            } else {
                try {
                    Integer back = tagDAO.addTag(gname, nid);
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
     * @name   delTag
     * @mark   删除分类
     * @access Admin
     * @URL    /adminApi/delTag.do
     * @method GET
     * @param  gid
     * @param  session
     * @return
     *
     */
    @GetMapping(value = "/adminApi/delTag.do")
    public Object delTag(Integer gid, HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        res.put("gid", gid);
        if (Checker.isAdmin(session)) {
            if (gid <= 0) {
                res.put("status", "empty");
            } else {
                try {
                    Integer back = tagDAO.delTag(gid);
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
     * @name   modTag
     * @mark   修改分类信息
     * @access Admin
     * @URL    /adminApi/modTag.do
     * @method POST
     * @param  gid
     * @param  gname
     * @param  nid
     * @param  session
     * @return
     *
     */
    @PostMapping(value = "/adminApi/modTag.do")
    public Object modTag(Integer gid, String gname, Integer nid, HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        res.put("gid", gid);
        if (Checker.isAdmin(session)) {
            if (gid <= 0) {
                res.put("status", "empty");
            } else {
                try {
                    Integer back = tagDAO.modTag(gid, gname, nid);
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
