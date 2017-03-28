package com.dyl.library;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by dengyulin on 16/6/22.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AdapterChildView {
    /**
     * findview 的ID
     * */
    int value() default -1;
    /**
     * 属于哪个view 从0开始 顺序为view加载顺序
     * */
    int[] type() default {0};

}
