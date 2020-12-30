package com.xxx.xlt.config;

import com.xxx.xlt.config.utils.PersonUtil;
import org.apache.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DoSomething implements ApplicationRunner {
    private static Logger logger = Logger.getLogger(DoSomething.class);
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("name="+ PersonUtil.getName());
        logger.info("number="+PersonUtil.getNumber());
    }
}
