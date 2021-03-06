package com.example.rabbitmq;

import com.example.excute.ExcuteCase;
import com.example.excute.TestNGSimpleReport;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.testng.TestNG;
import java.util.Arrays;

/**
 * @Auther: Leo.hu
 * @Date: 2019/11/25 15:14
 * @Description:
 */

@Component
@RabbitListener(queues = "ExcuteTest")
public class ConsumeMq {

    private static Integer retryCount = 0;

    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ConsumeMq.class);

    @RabbitHandler
    public void consume(Integer caseId) {
        synchronized (this){
            retryCount++;
            TestNG testNG = new TestNG();
            Class[] listenerClass = {TestNGSimpleReport.class};
            testNG.setListenerClasses(Arrays.asList(listenerClass));
            testNG.setTestClasses(new Class[]{ExcuteCase.class});
            LOGGER.info("当前执行次数为第" + getRetryCount() + "次, CaseId为：" + caseId);
            if (retryCount < 5) {
                testNG.run();
            }
        }
    }

    public static Integer getRetryCount() {
        return retryCount;
    }

    public static void setRetryCount(Integer retryCount) {
        ConsumeMq.retryCount = retryCount;
    }
}
