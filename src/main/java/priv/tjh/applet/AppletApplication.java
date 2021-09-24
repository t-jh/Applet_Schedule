package priv.tjh.applet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("priv.tjh.applet.model.dao")
public class AppletApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppletApplication.class, args);
    }

}
