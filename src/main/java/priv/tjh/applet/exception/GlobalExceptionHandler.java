package priv.tjh.applet.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.tjh.applet.common.ApiRestResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception exception) {
        return ApiRestResponse.error(AppletExceptionEnum.SYSTEM_ERROR);
    }

    @ExceptionHandler(AppletException.class)
    @ResponseBody
    public Object handleAppletException(AppletException appletException) {
        return ApiRestResponse.error(appletException.getCode(), appletException.getMsg());
    }

}
