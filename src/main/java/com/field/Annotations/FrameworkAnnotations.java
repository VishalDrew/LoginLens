package com.field.Annotations;


import com.field.Enums.TestCaseCategory;

import java.lang.annotation.*;


/**
 * FrameworkAnnotations is a custom annotation used for specifying metadata related to test cases,
 * such as the author and category of the test.
 * 
 * <p>This annotation can be applied to methods and is retained at runtime, 
 * allowing the metadata to be accessed via reflection during test execution.</p>
 * 
 * 
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface FrameworkAnnotations {

    /**
     * Specifies the author(s) of the test case.
     * 
     * @return An array of author names.
     */
    String[] author() default "Unknown Author";

    /**
     * Specifies the category/categories of the test case.
     * 
     * @return An array of {@code TestCaseCategory} enums.
     */
    TestCaseCategory[] category() default TestCaseCategory.UNCATEGORIZED;

}
