package com.decamincow.myspring.factory;

import com.decamincow.myspring.annotation.MyAutowired;
import com.decamincow.myspring.annotation.MyComponent;
import com.decamincow.myspring.annotation.MyTransactional;
import com.decamincow.myspring.utils.TransactionManager;
import lombok.Data;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author 应癫
 *
 *
 * 代理对象工厂：生成代理对象的
 */
@Data
@MyComponent
public class ProxyFactory {

    @MyAutowired
    private TransactionManager transactionManager;

    /**
     * 使用cglib动态代理生成代理对象
     * @param obj 委托对象
     * @return
     */
    public Object getCglibProxy(Object obj) {
        return  Enhancer.create(obj.getClass(), new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object result = null;
                MyTransactional transactional = method.getAnnotation(MyTransactional.class);
                // 如果有事务注解则执行事务操作
                if(transactional instanceof MyTransactional){
                    try{
                        // 开启事务(关闭事务的自动提交)
                        transactionManager.beginTransaction();

                        result = method.invoke(obj,objects);

                        // 提交事务
                        transactionManager.commit();
                    }catch (Exception e) {
                        e.printStackTrace();
                        // 回滚事务
                        transactionManager.rollback();

                        // 抛出异常便于上层servlet捕获
                        throw e;

                    }
                }else{
                    result = method.invoke(obj,objects);
                }
                return result;
            }
        });
    }
}
