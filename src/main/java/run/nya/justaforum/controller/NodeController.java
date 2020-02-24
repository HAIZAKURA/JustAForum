package run.nya.justaforum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import run.nya.justaforum.model.bean.Node;
import run.nya.justaforum.model.dao.NodeDAO;
import run.nya.justaforum.utils.Checker;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class NodeController {

    /**
     * status error 权限不足
     * status empty 参数为空
     * status success 操作成功
     * status fail 操作失败
     */

    @Autowired(required = false)
    private NodeDAO nodeDAO;

    /**
     *
     * @name   getAllNode
     * @mark   获取所有节点信息
     * @access All
     * @URL    /commApi/getAllNode.do
     * @method GET
     * @return
     *
     */
    @GetMapping(value = "/commApi/getAllNode.do")
    public Object getAllNode() {
        List<Map<String, Object>> res = new ArrayList<>();
        List<Node> allNode = nodeDAO.getAllNode();
        for (Node node : allNode) {
            Map<String, Object> map = new HashMap<>();
            map.put("nid", node.getNid());
            map.put("nname", node.getNname());
            map.put("ncont", node.getNcont());
            res.add(map);
        }
        return res;
    }

    /**
     *
     * @name   delNode
     * @mark   删除节点
     * @access Admin
     * @URL    /adminApi/delNode.do
     * @method GET
     * @param  nid
     * @param  session
     * @return
     *
     */
    @GetMapping(value = "/adminApi/delNode.do")
    public Object delNode(Integer nid, HttpSession session){
        Map<String, Object> res = new HashMap<>();
        res.put("nid", nid);
        if (Checker.isAdmin(session)) {
            if (nid == 0) {
                res.put("status", "empty");
            } else {
                try {
                    Integer back = nodeDAO.delNode(nid);
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
     * @name   addNode
     * @mark   新增节点
     * @access Admin
     * @URL    /adminApi/addNode.do
     * @method POST
     * @param  nname
     * @param  ncont
     * @param  session
     * @return
     *
     */
    @PostMapping(value = "/admin/addNode.do")
    public Object addNode(String nname, String ncont, HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        res.put("nname", nname);
        if (Checker.isAdmin(session)) {
            if (StringUtils.isEmpty(nname)) {
                res.put("status", "empty");
            } else {
                try {
                    Integer back = nodeDAO.addNode(nname, ncont);
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
     * @name   modNode
     * @mark   修改节点信息
     * @access Admin
     * @URL    /adminApi/modNode.do
     * @method POST
     * @param  nid
     * @param  nname
     * @param  ncont
     * @param  session
     * @return
     * 
     */
    @PostMapping(value = "/adminApi/modNode.do")
    public Object modNode(Integer nid, String nname, String ncont, HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        res.put("nid", nid);
        if (Checker.isAdmin(session)) {
            if (nid == 0) {
                res.put("status", "empty");
            } else {
                try {
                    Integer back = nodeDAO.modNode(nid, nname, ncont);
                    res.put("status", "success");
                } catch (Exception e) {
//                    e.printStackTrace();
                    res.put("status", "fail");
                }
            }
        } else {
            res.put("status", "fail");
        }
        return res;
    }

}
