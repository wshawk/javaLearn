package com.hawk.demo.validator.demo.validator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BaseEnumValidator {

    public static boolean isInEnum(EnumValid annotation, String value) throws
            NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object[] objects = getEnumConstants(annotation);
        Method method = getEnumMethod(annotation);
        for (Object o : objects) {
            if (value.equals(method.invoke(o))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInEnum(EnumValid annotation, Integer value) throws
            NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object[] objects = getEnumConstants(annotation);
        Method method = getEnumMethod(annotation);
        for (Object o : objects) {
            if (value.equals(method.invoke(o))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInEnumByListString(EnumValid annotation, List<String> valueList) throws Exception {
        Object[] objects = getEnumConstants(annotation);
        Method method = getEnumMethod(annotation);

        Set<String> set = new HashSet<>();
        for (Object o : objects) {
            set.add((String) method.invoke(o));
        }

        for (String value : valueList) {
            if (!set.contains(value)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isInEnumByListInteger(EnumValid annotation, List<Integer> valueList) throws Exception {
        Object[] objects = getEnumConstants(annotation);
        Method method = getEnumMethod(annotation);

        Set<Integer> set = new HashSet<>();
        for (Object o : objects) {
            set.add((Integer) method.invoke(o));
        }

        for (Integer value : valueList) {
            if (!set.contains(value)) {
                return false;
            }
        }
        return true;
    }

    private static Object[] getEnumConstants(EnumValid annotation) {
        return annotation.enumClass().getEnumConstants();
    }

    private static Method getEnumMethod(EnumValid annotation) throws NoSuchMethodException {
        return annotation.enumClass().getMethod(annotation.method());
    }
}
