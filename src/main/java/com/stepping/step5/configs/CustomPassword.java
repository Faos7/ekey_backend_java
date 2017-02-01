package com.stepping.step5.configs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.Random;

/**
 * Created by root on 1/31/17.
 */
@Target(ElementType.LOCAL_VARIABLE)
public @interface CustomPassword {
    String info() default "";
    int length = 8;


}
