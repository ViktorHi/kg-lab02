package com.vikras.model;

@FunctionalInterface
public interface ThrowingFunction<T, E extends Throwable, S>{
    S accept(T t) throws E;
}


