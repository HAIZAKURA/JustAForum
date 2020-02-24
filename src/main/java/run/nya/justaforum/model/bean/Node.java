package run.nya.justaforum.model.bean;

import java.io.Serializable;

public class Node implements Serializable {

    private static final long serialVersionUID = 5675020438928204923L;

    // 节点ID
    private Integer nid;
    // 节点名
    private String nname;
    // 节点简介
    private String ncont;

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname;
    }

    public String getNcont() {
        return ncont;
    }

    public void setNcont(String ncont) {
        this.ncont = ncont;
    }

}
