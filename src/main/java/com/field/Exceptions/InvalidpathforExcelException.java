package com.field.Exceptions;

/**
 * The {@code InvalidpathforExcelException} class is a custom exception used to signal errors related to invalid
 * paths for Excel files.
 * 
 * <p>This exception extends {@code RuntimeException} and is used to handle cases where an Excel file path provided
 * to the framework is incorrect or invalid. It provides constructors to include an error message and/or a cause for
 * the exception.</p>
 * 
 * <p>Example usage:</p>
 * <pre>
 * if (invalidPath) {
 *     throw new InvalidpathforExcelException("The path provided for the Excel file is invalid.");
 * }
 * </pre>
 * 
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
public class InvalidpathforExcelException extends RuntimeException {

    /**
     * Constructs a new {@code InvalidpathforExcelException} with the specified detail message.
     * 
     * @param message the detail message, which is saved for later retrieval by the {@code getMessage()} method.
     */
    public InvalidpathforExcelException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code InvalidpathforExcelException} with the specified detail message and cause.
     * 
     * @param message the detail message, which is saved for later retrieval by the {@code getMessage()} method.
     * @param cause the cause (which is saved for later retrieval by the {@code getCause()} method). 
     *              (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public InvalidpathforExcelException(String message, Throwable cause) {
        super(message, cause);
    }
}
