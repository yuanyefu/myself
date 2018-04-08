package com.docker.yyf.base;

import org.springframework.data.domain.Sort;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface DBIndex {
    boolean index() default true;
    boolean unique() default false;
    Sort.Direction direction() default  Sort.Direction.ASC;
}
