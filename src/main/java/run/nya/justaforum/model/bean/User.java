package run.nya.justaforum.model.bean;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 4458305759773696726L;

    // 用户ID
    private Integer uid;
    // 用户名
    private String uname;
    // 用户密码
    private String upass;
    // 用户邮箱
    private String umail;
    // 用户权限
    private Integer uacce;
    // 用户积分
    private Integer upoit;
    // 用户状态
    private Integer ustat;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    public String getUmail() {
        return umail;
    }

    public void setUmail(String umail) {
        this.umail = umail;
    }

    public Integer getUpoit() {
        return upoit;
    }

    public void setUpoit(Integer upoit) {
        this.upoit = upoit;
    }

    public Integer getUstat() {
        return ustat;
    }

    public void setUstat(Integer ustat) {
        this.ustat = ustat;
    }

    public Integer getUacce() {
        return uacce;
    }

    public void setUacce(Integer uacce) {
        this.uacce = uacce;
    }

}