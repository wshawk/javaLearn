package com.example.hawk.java_8.annotation;

/**
 * @author hawk
 * @package com.example.hawk.java_8.annotation
 * @desc
 * @date 2021/7/30
 */
public class HandleAnnotation {
    public static String getAnnotationValue(Class<?> clazz){
        if (clazz.isAnnotationPresent(MyAnnotation.class)){
            MyAnnotation myAnnotation = clazz.getAnnotation(MyAnnotation.class);
            return myAnnotation.name();
        }
        return "no value";
    }
}
