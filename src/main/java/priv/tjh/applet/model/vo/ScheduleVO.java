package priv.tjh.applet.model.vo;

import java.io.Serializable;

public class ScheduleVO implements Serializable {
    private Integer dgksdm;
    private String jxbmc;
    private Integer pkrs;
    private String kcmc;
    private String teaxms;
    private Integer xq;
    private Integer jcdm;
    private String jcdm2;
    private String jxcdmc;
    private Integer zc;
    private Integer kxh;
    private String jxhjmc;
    private String flfzmc;
    private String sknrjj;

    @Override
    public String toString() {
        return "ScheduleVO{" +
                "dgksdm=" + dgksdm +
                ", jxbmc='" + jxbmc + '\'' +
                ", pkrs=" + pkrs +
                ", kcmc='" + kcmc + '\'' +
                ", teaxms='" + teaxms + '\'' +
                ", xq=" + xq +
                ", jcdm=" + jcdm +
                ", jcdm2='" + jcdm2 + '\'' +
                ", jxcdmc='" + jxcdmc + '\'' +
                ", zc=" + zc +
                ", kxh=" + kxh +
                ", jxhjmc='" + jxhjmc + '\'' +
                ", flfzmc='" + flfzmc + '\'' +
                ", sknrjj='" + sknrjj + '\'' +
                '}';
    }

    public ScheduleVO(Integer dgksdm, String jxbmc, Integer pkrs, String kcmc, String teaxms, Integer xq, Integer jcdm, String jcdm2, String jxcdmc, Integer zc, Integer kxh, String jxhjmc, String flfzmc, String sknrjj) {
        this.dgksdm = dgksdm;
        this.jxbmc = jxbmc;
        this.pkrs = pkrs;
        this.kcmc = kcmc;
        this.teaxms = teaxms;
        this.xq = xq;
        this.jcdm = jcdm;
        this.jcdm2 = jcdm2;
        this.jxcdmc = jxcdmc;
        this.zc = zc;
        this.kxh = kxh;
        this.jxhjmc = jxhjmc;
        this.flfzmc = flfzmc;
        this.sknrjj = sknrjj;
    }

    public ScheduleVO() {
    }

    public Integer getDgksdm() {
        return dgksdm;
    }

    public void setDgksdm(Integer dgksdm) {
        this.dgksdm = dgksdm;
    }

    public String getJxbmc() {
        return jxbmc;
    }

    public void setJxbmc(String jxbmc) {
        this.jxbmc = jxbmc;
    }

    public Integer getPkrs() {
        return pkrs;
    }

    public void setPkrs(Integer pkrs) {
        this.pkrs = pkrs;
    }

    public String getKcmc() {
        return kcmc;
    }

    public void setKcmc(String kcmc) {
        this.kcmc = kcmc;
    }

    public String getTeaxms() {
        return teaxms;
    }

    public void setTeaxms(String teaxms) {
        this.teaxms = teaxms;
    }

    public Integer getXq() {
        return xq;
    }

    public void setXq(Integer xq) {
        this.xq = xq;
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
        this.jcdm2 = jcdm2;
    }

    public String getJxcdmc() {
        return jxcdmc;
    }

    public void setJxcdmc(String jxcdmc) {
        this.jxcdmc = jxcdmc;
    }

    public Integer getZc() {
        return zc;
    }

    public void setZc(Integer zc) {
        this.zc = zc;
    }

    public Integer getKxh() {
        return kxh;
    }

    public void setKxh(Integer kxh) {
        this.kxh = kxh;
    }

    public String getJxhjmc() {
        return jxhjmc;
    }

    public void setJxhjmc(String jxhjmc) {
        this.jxhjmc = jxhjmc;
    }

    public String getFlfzmc() {
        return flfzmc;
    }

    public void setFlfzmc(String flfzmc) {
        this.flfzmc = flfzmc;
    }

    public String getSknrjj() {
        return sknrjj;
    }

    public void setSknrjj(String sknrjj) {
        this.sknrjj = sknrjj;
    }
}