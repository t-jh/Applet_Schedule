package priv.tjh.applet.common;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constant {

    public static HttpClient httpClient = HttpClients.createDefault();

    public static Boolean checkLogin = false;

    public static String FILE_UPLOAD_DIR;

    @Value("${file_upload_dir}")
    public void setFileUploadDir(String fileUploadDir) {
        FILE_UPLOAD_DIR = fileUploadDir;
    }

    public static String xnxqdm;

    @Value("${xnxqdm}")
    public void setXnxqdm(String xnxqdm) {
        Constant.xnxqdm = xnxqdm;
    }
}
