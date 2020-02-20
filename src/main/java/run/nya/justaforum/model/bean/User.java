package run.nya.justaforum.model.bean;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 4458305759773696726L;

    private Integer uid;
    private String uname;
    private String upass;
    private String umail;
    private Integer uacce;
    private Integer upoit;

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

    public Integer getUacce() {
        return uacce;
    }

    public void setUacce(Integer uacce) {
        this.uacce = uacce;
    }

    public Integer getUpoit() {
        return upoit;
    }

    public void setUpoit(Integer upoit) {
        this.upoit = upoit;
    }

}