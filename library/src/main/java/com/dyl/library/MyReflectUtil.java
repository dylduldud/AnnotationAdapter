package com.dyl.library;

import java.lang.reflect.Field;
import java.util.HashMap;

public class MyReflectUtil {
    private static final String FIELDS = "fields";
    private final static HashMap<Class, HashMap<String, Object>> fieldsMap = new HashMap<Class, HashMap<String, Object>>();

    public static Field[] getFields(Class clazz) {
        if (fieldsMap.containsKey(clazz)) {
            return (Field[]) fieldsMap.get(clazz).get(FIELDS);
        } else {
            HashMap<String, Object> map = new HashMap<String, Object>();
            Field[] fields = clazz.getDeclaredFields();
            map.put(FIELDS, fields);
            fieldsMap.put(clazz, map);
            return fields;
        }
    }

}
