package com.ghd.annocation;

import java.lang.annotation.*;

/**
 * @author ghd-alem
 * @description
 * @date 2020/5/28 11:26
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExplicitConstraint {
    //定义固定下拉内容
    String[]source()default {};
    //定义动态下拉内容，
    Class[]sourceClass()default {};
}
