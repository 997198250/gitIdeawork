package com.fh.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // 在属性上用  包含枚举的常量
@Retention(RetentionPolicy.RUNTIME) // jvm加载 在类的文件中 运行时可以获取到
public @interface ExcelFild {
    //default 默认值  列头名
    String name() default "";
}
