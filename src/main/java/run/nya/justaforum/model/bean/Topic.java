package run.nya.justaforum.model.bean;

import java.io.Serializable;

public class Topic implements Serializable {

    private static final long serialVersionUID = -7404629555556097888L;

    // 话题ID
    private Integer tid;
    // 话题名
    private String tname;
    // 话题内容
    private String tcont;
    // 话题创建时间
    private String tdate;
    // 所属用户ID
    private Integer uid;
    // 所属节点ID
    private Integer nid;
    // 所属分类ID
    private Integer gid;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTcont() {
        return tcont;
    }

    public void setTcont(String tcont) {
        this.tcont = tcont;
    }

    public String getTdate() {
        return tdate;
    }

    public void setTdate(String tdate) {
        this.tdate = tdate;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

}
