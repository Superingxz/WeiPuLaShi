package com.xolo.weipulashi.utils;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Administrator on 2017/1/10.
 */

public class InstantUtils {
    /**
     * 通过实例工厂去实例化相应类
     *
     * @param o   带泛型的对象
     * @param i   需要实例化的对象在泛型中的位置
     * @param <T> 返回实例的泛型类型
     * @return
     */
    public static  <T> T getInstance(Object o, int i) throws IllegalAccessException, java.lang.InstantiationException {
        if (o.getClass().getGenericSuperclass() instanceof ParameterizedType) {
            Class mClass = (Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i];
            T t= (T) mClass.newInstance();
            return  t;
        }
        return null;
    }
}
