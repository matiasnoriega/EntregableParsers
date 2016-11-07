package com.example.matias.entregableparsers.util;

public interface ResultListener<T> {
    void finish(T resultado);
}