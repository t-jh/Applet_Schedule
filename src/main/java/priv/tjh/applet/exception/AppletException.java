package priv.tjh.applet.exception;

public class AppletException extends RuntimeException{
    private Integer code;
    private String msg;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public AppletException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public AppletException(AppletExceptionEnum appletExceptionEnum) {
        this(appletExceptionEnum.getCode(), appletExceptionEnum.getMsg());
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
