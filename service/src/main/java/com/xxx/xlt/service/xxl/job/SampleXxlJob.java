package com.xxx.xlt.service.xxl.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxx.xlt.utils.common.AppContextUtil;
import com.xxx.xlt.utils.common.MailServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * XxlJob开发示例（Bean模式）
 *
 * 开发步骤：
 *      1、任务开发：在Spring Bean实例中，开发Job方法；
 *      2、注解配置：为Job方法添加注解 "@XxlJob(value="自定义jobhandler名称", init = "JobHandler初始化方法", destroy = "JobHandler销毁方法")"，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 *      3、执行日志：需要通过 "XxlJobHelper.log" 打印执行日志；
 *      4、任务结果：默认任务结果为 "成功" 状态，不需要主动设置；如有诉求，比如设置任务结果为失败，可以通过 "XxlJobHelper.handleFail/handleSuccess" 自主设置任务结果；
 *
 * @author xuxueli 2019-12-11 21:52:51
 */
@Component
public class SampleXxlJob {
    private static Logger logger = LoggerFactory.getLogger(SampleXxlJob.class);


    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("demoJobHandler")
    public ReturnT<String> demoJobHandler(String param) throws Exception {
        logger.info("XXL-JOB, Hello World.");
        MailServiceUtil mailServiceUtil = AppContextUtil.getBean(MailServiceUtil.class);

        for (int i = 0; i < 5; i++) {
            XxlJobLogger.log("beat at:" + i);
            TimeUnit.MILLISECONDS.sleep(20);
        }
        mailServiceUtil.sendSimpleMail("xulitao666@gmail.com","我爱美蓉蓉","我爱曾莹蓉，她是的最爱，我的大宝贝");
//        mailServiceUtil.sendAttachmentsMail("xulitao666@gmail.com","我爱美蓉蓉","我爱曾莹蓉，她是的最爱，我的大宝贝","D:\\MyProjects\\JAVA\\AlexonPlatform\\simpleWrite1612367081267.xlsx");

        return ReturnT.SUCCESS;
    }

}
