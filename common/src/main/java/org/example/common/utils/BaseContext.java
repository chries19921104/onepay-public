package org.example.common.utils;

import java.lang.reflect.Array;

public class BaseContext {
    private static ThreadLocal<Long> threadLocal=new ThreadLocal<>();
    public static void setCurrent(Long id){

        threadLocal.set(id);
    }
    public static Long getCurrent(){
        return threadLocal.get();
    }
    public static void remove(){
        threadLocal.remove();
    }
}
