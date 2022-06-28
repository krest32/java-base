package JavaSE.Log;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用的是Logback的实现框架
 */
@Slf4j
public class LogDemo1 {

    @Test
    public void test() {
        Logger logger = LoggerFactory.getLogger(LogDemo1.class);
        logger.info("Hello World");
        // 日志的级别由低到高，日志级别只会显示更高级别，logback默认使用debug级别
        // 默认不显示
        logger.trace("这是日志");
        logger.debug("这是调试");
        logger.info("这是info");
        logger.warn("警告");
        logger.error("error");
    }
}
