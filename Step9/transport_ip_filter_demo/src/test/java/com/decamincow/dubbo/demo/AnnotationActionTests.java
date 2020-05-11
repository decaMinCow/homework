package com.decamincow.dubbo.demo;

import com.decamincow.dubbo.demo.Constants.TPConstans;
import com.decamincow.dubbo.demo.action.AnnotationAction;
import com.decamincow.dubbo.demo.model.MyTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@SpringBootTest
@EnableScheduling
public class AnnotationActionTests {

    @Autowired
    private AnnotationAction annotationAction;

    @Test
    void contextLoads() throws Exception {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(50);
            threadPool.execute(() -> {
                annotationAction.doSayHello("decamincow");
                annotationAction.doGreeting("decamincow");
                annotationAction.doRun("decamincow");
            });
        }
        threadPool.shutdown();
    }

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("最近一分钟函数 GREET TP90: " + tp(TPConstans.GREETING_TP_LIST, 90) + "毫秒");
        System.out.println("最近一分钟函数 GREET TP99: " + tp(TPConstans.GREETING_TP_LIST, 99) + "毫秒");
        System.out.println("最近一分钟函数 HELLO TP90: " + tp(TPConstans.SAY_HELLO_TP_LIST, 90) + "毫秒");
        System.out.println("最近一分钟函数 HELLO TP99: " + tp(TPConstans.SAY_HELLO_TP_LIST, 99) + "毫秒");
        System.out.println("最近一分钟函数 RUN TP90: " + tp(TPConstans.RUN_TP_LIST, 90) + "毫秒");
        System.out.println("最近一分钟函数 RUN TP99: " + tp(TPConstans.RUN_TP_LIST, 99) + "毫秒");
        System.out.println("=============================================");
    }

    private static Long tp(List<MyTime> times, int percent) {
        List<MyTime> timeFilter = times.stream().filter(s->s.getRecordTime() >= (System.currentTimeMillis() - 1000 * 60))
                .collect(Collectors.toList());
        timeFilter.sort(Comparator.comparing(MyTime::getResponseTime));
        System.out.println(timeFilter);
        if (timeFilter.size() > 0){
            float percentF = (float)percent/100;
            int index = (int)(percentF * timeFilter.size() - 1);
            return timeFilter.get(index).getResponseTime();
        }else{
            return 0L;
        }
    }

}