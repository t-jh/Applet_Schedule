package priv.tjh.applet.model.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Announcement implements Serializable {
    private Integer ggtzdm;

    private String bt;

    private String fbfw;

    private Integer istop;

    private String summary;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date cjsj;

    @Override
    public String toString() {
        return "Announcement{" +
                "ggtzdm=" + ggtzdm +
                ", bt='" + bt + '\'' +
                ", fbfw='" + fbfw + '\'' +
                ", istop=" + istop +
                ", summary='" + summary + '\'' +
                ", cjsj=" + cjsj +
                '}';
    }

    public Integer getGgtzdm() {
        return ggtzdm;
    }

    public void setGgtzdm(Integer ggtzdm) {
        this.ggtzdm = ggtzdm;
    }

    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt == null ? null : bt.trim();
    }

    public String getFbfw() {
        return fbfw;
    }

    public void setFbfw(String fbfw) {
        this.fbfw = fbfw == null ? null : fbfw.trim();
    }

    public Integer getIstop() {
        return istop;
    }

    public void setIstop(Integer istop) {
        this.istop = istop;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }
}