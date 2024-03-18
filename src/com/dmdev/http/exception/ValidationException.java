package com.dmdev.http.exception;

import com.dmdev.http.validator.Error;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ValidationException extends RuntimeException {

    private final List<Error> errors;

}
