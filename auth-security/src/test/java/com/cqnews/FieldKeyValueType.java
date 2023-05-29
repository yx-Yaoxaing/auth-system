package com.cqnews;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FieldKeyValueType {

    private Map<String,UserMapper> map;

    public static void main(String[] args) {
        Class<FieldKeyValueType> fieldKeyValueTypeClass = FieldKeyValueType.class;

        Field[] declaredFields = fieldKeyValueTypeClass.getDeclaredFields();
        for (Field field : declaredFields) {
            Class<?> type = field.getType();
            if (Map.class==type) {
                ParameterizedType genericType = (ParameterizedType) field.getGenericType();
                Type[] actualTypeArguments = genericType.getActualTypeArguments();
                for (Type actualType : actualTypeArguments) {
                    String typeName = actualType.getTypeName();
                    System.err.println(typeName);
                }
            } else if (type.isArray()) {

            }
        }
    }

}
