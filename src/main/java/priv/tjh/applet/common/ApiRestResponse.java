package priv.tjh.applet.common;

import priv.tjh.applet.exception.AppletExceptionEnum;

public class ApiRestResponse<T> {

    private Integer status;
    private String msg;
    private T data;
    private static final int OK_CODE = 10000;
    private static final String OK_MSG = "SUCCESS";

    public ApiRestResponse(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ApiRestResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ApiRestResponse() {
        this(OK_CODE, OK_MSG);
    }

    public static ApiRestResponse success() {
        return new ApiRestResponse();
    }

    public static <T> ApiRestResponse success(T data) {
        ApiRestResponse<Object> apiRestResponse = new ApiRestResponse<>();
        apiRestResponse.setData(data);
        return apiRestResponse;
    }

    public static ApiRestResponse error(Integer code, String msg) {
        return new ApiRestResponse(code, msg);
    }

    public static ApiRestResponse error(AppletExceptionEnum appletExceptionEnum) {
        return new ApiRestResponse(appletExceptionEnum.getCode(), appletExceptionEnum.getMsg());
    }

    @Override
    public String toString() {
        return "ApiRestResponse{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static int getOkCode() {
        return OK_CODE;
    }

    public static String getOkMsg() {
        return OK_MSG;
    }
}
