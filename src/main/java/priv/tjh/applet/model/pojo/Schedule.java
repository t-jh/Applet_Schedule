package priv.tjh.applet.model.pojo;

import java.io.Serializable;

public class Schedule implements Serializable {
    private Integer dgksdm;

    private String kbdm;

    private String kcbh;

    private String kcmc;

    private String teaxms;

    private String jxbdm;

    private String xnxqdm;

    private String jxbmc;

    private Integer zc;

    private Integer jcdm;

    private String jcdm2;

    private Integer xq;

    private String jxcdmc;

    private String sknrjj;

    private String teadms;

    private String jxcddm;

    private String kcdm;

    private Integer zxs;

    private Integer xs;

    private Integer pkrs;

    private Integer kxh;

    private String flfzmc;

    private String jxhjmc;

    private Integer tkbz;

    @Override
    public String toString() {
        return "Schedule{" +
                "dgksdm=" + dgksdm +
                ", kbdm='" + kbdm + '\'' +
                ", kcbh='" + kcbh + '\'' +
                ", kcmc='" + kcmc + '\'' +
                ", teaxms='" + teaxms + '\'' +
                ", jxbdm='" + jxbdm + '\'' +
                ", xnxqdm='" + xnxqdm + '\'' +
                ", jxbmc='" + jxbmc + '\'' +
                ", zc=" + zc +
                ", jcdm='" + jcdm + '\'' +
                ", jcdm2='" + jcdm2 + '\'' +
                ", xq=" + xq +
                ", jxcdmc='" + jxcdmc + '\'' +
                ", sknrjj='" + sknrjj + '\'' +
                ", teadms='" + teadms + '\'' +
                ", jxcddm='" + jxcddm + '\'' +
                ", kcdm='" + kcdm + '\'' +
                ", zxs=" + zxs +
                ", xs=" + xs +
                ", pkrs=" + pkrs +
                ", kxh=" + kxh +
                ", flfzmc='" + flfzmc + '\'' +
                ", jxhjmc='" + jxhjmc + '\'' +
                ", tkbz=" + tkbz +
                '}';
    }

    public Schedule(Integer dgksdm, String kbdm, String kcbh, String kcmc, String teaxms, String jxbdm, String xnxqdm, String jxbmc, Integer zc, Integer jcdm, String jcdm2, Integer xq, String jxcdmc, String sknrjj, String teadms, String jxcddm, String kcdm, Integer zxs, Integer xs, Integer pkrs, Integer kxh, String flfzmc, String jxhjmc, Integer tkbz) {
        this.dgksdm = dgksdm;
        this.kbdm = kbdm;
        this.kcbh = kcbh;
        this.kcmc = kcmc;
        this.teaxms = teaxms;
        this.jxbdm = jxbdm;
        this.xnxqdm = xnxqdm;
        this.jxbmc = jxbmc;
        this.zc = zc;
        this.jcdm = jcdm;
        this.jcdm2 = jcdm2;
        this.xq = xq;
        this.jxcdmc = jxcdmc;
        this.sknrjj = sknrjj;
        this.teadms = teadms;
        this.jxcddm = jxcddm;
        this.kcdm = kcdm;
        this.zxs = zxs;
        this.xs = xs;
        this.pkrs = pkrs;
        this.kxh = kxh;
        this.flfzmc = flfzmc;
        this.jxhjmc = jxhjmc;
        this.tkbz = tkbz;
    }

    public Schedule() {
    }

    public Integer getDgksdm() {
        return dgksdm;
    }

    public void setDgksdm(Integer dgksdm) {
        this.dgksdm = dgksdm;
    }

    public String getKbdm() {
        return kbdm;
    }

    public void setKbdm(String kbdm) {
        this.kbdm = kbdm == null ? null : kbdm.trim();
    }

    public String getKcbh() {
        return kcbh;
    }

    public void setKcbh(String kcbh) {
        this.kcbh = kcbh == null ? null : kcbh.trim();
    }

    public String getKcmc() {
        return kcmc;
    }

    public void setKcmc(String kcmc) {
        this.kcmc = kcmc == null ? null : kcmc.trim();
    }

    public String getTeaxms() {
        return teaxms;
    }

    public void setTeaxms(String teaxms) {
        this.teaxms = teaxms == null ? null : teaxms.trim();
    }

    public String getJxbdm() {
        return jxbdm;
    }

    public void setJxbdm(String jxbdm) {
        this.jxbdm = jxbdm == null ? null : jxbdm.trim();
    }

    public String getXnxqdm() {
        return xnxqdm;
    }

    public void setXnxqdm(String xnxqdm) {
        this.xnxqdm = xnxqdm == null ? null : xnxqdm.trim();
    }

    public String getJxbmc() {
        return jxbmc;
    }

    public void setJxbmc(String jxbmc) {
        this.jxbmc = jxbmc == null ? null : jxbmc.trim();
    }

    public Integer getZc() {
        return zc;
    }

    public void setZc(Integer zc) {
        this.zc = zc;
    }

    public Integer getJcdm() {
        return jcdm;
    }

    public void setJcdm(Integer jcdm) {
        this.jcdm = jcdm;
    }

    public String getJcdm2() {
        return jcdm2;
    }

    public void setJcdm2(String jcdm2) {
        this.jcdm2 = jcdm2 == null ? null : jcdm2.trim();
    }

    public Integer getXq() {
        return xq;
    }

    public void setXq(Integer xq) {
        this.xq = xq;
    }

    public String getJxcdmc() {
        return jxcdmc;
    }

    public void setJxcdmc(String jxcdmc) {
        this.jxcdmc = jxcdmc == null ? null : jxcdmc.trim();
    }

    public String getSknrjj() {
        return sknrjj;
    }

    public void setSknrjj(String sknrjj) {
        this.sknrjj = sknrjj == null ? null : sknrjj.trim();
    }

    public String getTeadms() {
        return teadms;
    }

    public void setTeadms(String teadms) {
        this.teadms = teadms == null ? null : teadms.trim();
    }

    public String getJxcddm() {
        return jxcddm;
    }

    public void setJxcddm(String jxcddm) {
        this.jxcddm = jxcddm == null ? null : jxcddm.trim();
    }

    public String getKcdm() {
        return kcdm;
    }

    public void setKcdm(String kcdm) {
        this.kcdm = kcdm == null ? null : kcdm.trim();
    }

    public Integer getZxs() {
        return zxs;
    }

    public void setZxs(Integer zxs) {
        this.zxs = zxs;
    }

    public Integer getXs() {
        return xs;
    }

    public void setXs(Integer xs) {
        this.xs = xs;
    }

    public Integer getPkrs() {
        return pkrs;
    }

    public void setPkrs(Integer pkrs) {
        this.pkrs = pkrs;
    }

    public Integer getKxh() {
        return kxh;
    }

    public void setKxh(Integer kxh) {
        this.kxh = kxh;
    }

    public String getFlfzmc() {
        return flfzmc;
    }

    public void setFlfzmc(String flfzmc) {
        this.flfzmc = flfzmc == null ? null : flfzmc.trim();
    }

    public String getJxhjmc() {
        return jxhjmc;
    }

    public void setJxhjmc(String jxhjmc) {
        this.jxhjmc = jxhjmc == null ? null : jxhjmc.trim();
    }

    public Integer getTkbz() {
        return tkbz;
    }

    public void setTkbz(Integer tkbz) {
        this.tkbz = tkbz;
    }
}