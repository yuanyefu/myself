package com.docker.yyf.entity;

public interface GetFunction<T> {
    String invoke(T t);
}
