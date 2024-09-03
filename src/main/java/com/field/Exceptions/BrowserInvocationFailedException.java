package com.field.Exceptions;

public class BrowserInvocationFailedException extends FrameWorkExceptions {

    public BrowserInvocationFailedException(String message) {
        super(message);
    }

    public BrowserInvocationFailedException(String message,Throwable cause) {
        super(message,cause);
    }
}

