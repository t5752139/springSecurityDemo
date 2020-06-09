package com.imooc.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;


/**
 * 异步处理
 */

@RestController

public class SyncController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * callable 异步处理
     * @return
     */
    @RequestMapping("sync")
    public Callable<String>  callable (){
        logger.info("主线程开始");
            //开启异步
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("父线程开始");
                Thread.sleep(5000);
                logger.info("父线程结束");
                return "success";
            }
        };
        logger.info("主线程结束");

        return callable;
    }


}
