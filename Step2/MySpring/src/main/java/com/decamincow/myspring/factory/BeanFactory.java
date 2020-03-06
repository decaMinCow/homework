package com.decamincow.myspring.factory;

import com.decamincow.myspring.annotation.*;
import com.decamincow.myspring.utils.ClassUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 应癫
 *
 * 工厂类，生产对象（使用反射技术）
 */
public class BeanFactory {

    private static Map<String,Object> map = new HashMap<>();  // 存储对象

    private final static String PACKAGE_NAME = "com.decamincow.myspring";


    static {
        // 扫描包下类
        List<Class<?>> classes = ClassUtil.getClasses(PACKAGE_NAME);
        // 放入容器汇总
        try {
            classes.forEach(classInfo -> {
                // TODO 以后再优化，先实现
                Annotation service = classInfo.getAnnotation(MyService.class);
                Annotation dao = classInfo.getAnnotation(MyDao.class);
                Annotation component = classInfo.getAnnotation(MyComponent.class);

                // 判断注解类型
                if (service instanceof MyService){
                    String beanID;
                    String annoValue = ((MyService) service).value();
                    Object o = null;  // 实例化之后的对象
                    // 判断注解值
                    if(!annoValue.isBlank()){
                        beanID = ClassUtil.toLowerCaseFirstOne(annoValue);
                    }else{
                        beanID = getBeanID(classInfo);
                    }
                    Class<?> aClass;
                    try {
                        aClass = Class.forName(classInfo.getName());
                        o = aClass.newInstance();
                    } catch (Exception e) {
                    }
                    map.put(beanID,o);
                }

                // 判断注解类型
                if (dao instanceof MyDao){
                    String beanID;
                    String annoValue = ((MyDao) dao).value();
                    Object o = null;  // 实例化之后的对象
                    // 判断注解值
                    if(!annoValue.isBlank()){
                        beanID = ClassUtil.toLowerCaseFirstOne(annoValue);
                    }else{
                        beanID = getBeanID(classInfo);
                    }
                    Class<?> aClass = null;
                    try {
                        aClass = Class.forName(classInfo.getName());
                        o = aClass.newInstance();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    map.put(beanID,o);
                }

                // 判断注解类型
                if (component instanceof MyComponent){
                    String beanID;
                    String annoValue = ((MyComponent) component).value();
                    Object o = null;  // 实例化之后的对象
                    // 判断注解值
                    if(!annoValue.isBlank()){
                        beanID = ClassUtil.toLowerCaseFirstOne(annoValue);
                    }else{
                        beanID = getBeanID(classInfo);
                    }
                    Class<?> aClass = null;
                    try {
                        aClass = Class.forName(classInfo.getName());
                        o = aClass.newInstance();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    map.put(beanID,o);
                }
            });

            // 依赖注入和事务处理
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object bean = entry.getValue();
                Class<? extends Object> classInfo = bean.getClass();
                // 用来判断属性
                Field[] declaredFields = classInfo.getDeclaredFields();
                // 用来判断函数
                Method[] methods = classInfo.getMethods();

                // 判断当前类属性是否存在注解
                Arrays.stream(declaredFields).forEach(field -> {
                    MyAutowired autowired = field.getAnnotation(MyAutowired.class);
                    if (autowired != null) {
                        // 获取属性名称
                        String beanId = field.getName();
                        Object beanFinal = getBean(beanId);
                        if (bean != null) {
                            // 3.默认使用属性名称，查找bean容器对象 1参数 当前对象 2参数给属性赋值
                            field.setAccessible(true); // 允许访问私有属性
                            try {
                                field.set(bean, beanFinal);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getBeanID(Class<?> classInfo){
        String className;
        if(classInfo.getInterfaces().length != 0){
            className = classInfo.getInterfaces()[0].getSimpleName();
        }else{
            className = classInfo.getSimpleName();
        }
        return ClassUtil.toLowerCaseFirstOne(className);
    }

    // 任务二：对外提供获取实例对象的接口（根据id获取）
    public static Object getBean(String id) {

        return map.get(id);
    }

}
