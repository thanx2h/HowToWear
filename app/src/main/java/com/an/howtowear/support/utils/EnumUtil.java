package com.an.howtowear.support.utils;

import com.an.howtowear.support.inter.HTWEnum;

public class EnumUtil {
    private static EnumUtil enumUtil;

    public static<T extends HTWEnum> String getValue(Class<T> clazz, String name){

        if(name == null || name.isEmpty()){
            return "Null";
        }


        for(T t : clazz.getEnumConstants()){
            if(t.getValue().equals(name)){
                return t.getName();
            }
        }

        return "None";
    }

    public static<T extends HTWEnum> String getName(Class<T> clazz, String value){

        if(value == null || value.isEmpty()){
            return "Null";
        }


        for(T t : clazz.getEnumConstants()){
            if(t.getValue().equals(value)){
                return t.getName();
            }
        }

        return "None";
    }
}
