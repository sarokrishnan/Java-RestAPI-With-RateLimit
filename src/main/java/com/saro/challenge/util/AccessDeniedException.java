package com.saro.challenge.util;

/**
 * Created by sbalakrishnan on 4/1/16.
 */

public class AccessDeniedException extends Exception {
    public AccessDeniedException(String message) {
        super(message);
    }
}