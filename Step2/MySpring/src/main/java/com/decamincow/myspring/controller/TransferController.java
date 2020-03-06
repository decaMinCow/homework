//package com.decamincow.myspring.controller;
//
//import com.decamincow.myspring.factory.BeanFactory;
//import com.decamincow.myspring.factory.ProxyFactory;
//import com.decamincow.myspring.service.TransferService;
//import com.decamincow.myspring.service.impl.TransferServiceImpl;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @ClassName TransferController
// * @Description TODO
// * @Author decamincow
// * @Date 06/03/2020 11:06 AM
// * @Version 1.0
// **/
//@RestController
//@RequestMapping("/api")
//public class TransferController {
//
//    @GetMapping(value = "/transfer")
//    public String transfer(){
//        String result = "SUCCESS";
//        try {
//            TransferService transferService = (TransferServiceImpl) BeanFactory.getBean("transferService");
//            transferService.transfer(2, 1, 200);
//        } catch (Exception e) {
//            e.printStackTrace();
//            result = "转账失败";
//        }
//        return result;
//    }
//
//}


package com.decamincow.myspring.controller;

import com.decamincow.myspring.factory.BeanFactory;
import com.decamincow.myspring.factory.ProxyFactory;
import com.decamincow.myspring.service.impl.TransferServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TransferController
 * @Description TODO
 * @Author decamincow
 * @Date 06/03/2020 11:06 AM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api")
public class TransferController {

    @GetMapping(value = "/transfer")
    public String transfer(){
        ProxyFactory proxyFactory = (ProxyFactory) BeanFactory.getBean("proxyFactory");
//        TransferService transferService = (TransferService) proxyFactory.getJdkProxy(BeanFactory.getBean("transferService")) ;
        TransferServiceImpl transferService = (TransferServiceImpl) proxyFactory.getCglibProxy(BeanFactory.getBean("transferService")) ;
        String result = "SUCCESS";
        try {
            transferService.transfer(2, 1, 200);
        } catch (Exception e) {
            e.printStackTrace();
            result = "转账失败";
        }
        return result;
    }

}
