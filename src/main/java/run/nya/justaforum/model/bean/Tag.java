package run.nya.justaforum.model.bean;

import java.io.Serializable;

public class Tag implements Serializable {

    private static final long serialVersionUID = 8606752545178021025L;

    // 分类ID
    private Integer gid;

    // 分类名
    private String gname;

    // 所属节点ID
    private Integer nid;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getGanme() {
        return gname;
    }

    public void setGanme(String ganme) {
        this.gname = ganme;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }
}
