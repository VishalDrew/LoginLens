package com.field.Enums;


/**
 * Enum representing different categories of test cases.
 * This enum can be used to classify test cases based on their context or platform.
 *
 *
 *  * @author Ezhilraj
 *  * @version 1.0
 *  * @since 1.0
 */



public enum TestCaseCategory {

    /**
     * Represents test cases related to mobile applications.
     * Use this category for tests that are specific to mobile platforms, such as Android or iOS.
     */
    MOBILE,

    /**
     * Represents test cases related to web applications.
     * Use this category for tests that are specific to web browsers and web-based platforms.
     */
    WEB,

    /**
     * Represents test cases that do not fall under a specific category.
     * Use this category for tests that are not explicitly defined as either mobile or web.
     */
    UNCATEGORIZED;
}

