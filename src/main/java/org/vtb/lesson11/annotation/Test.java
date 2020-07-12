package org.vtb.lesson11.annotation;

import jdk.jfr.Frequency;
import jdk.jfr.Unsigned;

import java.beans.BeanProperty;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
    int value() default 1;
}
