package com.field.Exceptions;

/**
 * The {@code FrameWorkExceptions} class is a custom exception class that extends {@code RuntimeException}.
 * It is used to represent exceptions specific to the framework, providing additional flexibility for error handling.
 * 
 * <p>This class provides constructors for creating exceptions with a message, a cause, or both. It is designed to
 * allow for more detailed error reporting and handling in the context of the framework.</p>
 * 
 * <p>Example usage:</p>
 * <pre>
 * try {
 *     // Code that might throw an exception
 * } catch (Exception e) {
 *     throw new FrameWorkExceptions("An error occurred in the framework", e);
 * }
 * </pre>
 * 
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
public class FrameWorkExceptions extends RuntimeException {

    /**
     * Constructs a new {@code FrameWorkExceptions} with the specified detail message.
     * 
     * @param message the detail message, which is saved for later retrieval by the {@code getMessage()} method.
     */
    public FrameWorkExceptions(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code FrameWorkExceptions} with the specified detail message and cause.
     * 
     * @param message the detail message, which is saved for later retrieval by the {@code getMessage()} method.
     * @param cause the cause (which is saved for later retrieval by the {@code getCause()} method). 
     *              (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public FrameWorkExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}

