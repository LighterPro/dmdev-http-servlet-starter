package com.dmdev.http.mapper;

// F - From, T - To
public interface Mapper<F, T> {
    T map(F object);
}
