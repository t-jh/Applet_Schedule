package priv.tjh.applet.exception;

public enum AppletExceptionEnum {
    /**
     *
     */
    SYSTEM_ERROR(10001, "系统错误"),
    NOT_VERIFYCODE(10002, "验证码获取失败"),
    MKDIR_FAILED(10003, "文件创建失败"),
    WRONG_LOGIN(10004, "您的帐号或密码不正确"),
    ERROR_LINK(10005, "连接失败"),
    NEED_LOGIN(10006, "用户未登录"),
    SAVE_FAILED(10007, "保存失败"),
    DELETE_FAILED(10008, "删除失败"),
    ACQUIRE_FAILED(10009, "获取失败"),

    ;

    private Integer code;
    private String msg;

    AppletExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
