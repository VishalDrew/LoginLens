package com.field.Listeners;

import com.field.DataProviderUtils.Dataprovider;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * The {@code AnnotationTransformer} class implements the {@code IAnnotationTransformer} interface to modify
 * test annotations at runtime.
 * <p>This class allows for customization of test annotations by setting the data provider, data provider class,
 * and retry analyzer for test methods.</p>
 * 
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
public class AnnotationTransformer implements IAnnotationTransformer {

    /**
     * Transforms the test annotations at runtime.
     * <p>This method is called by TestNG to allow modifications to test annotations. It sets the data provider,
     * data provider class, and retry analyzer for test methods.</p>
     * 
     * @param annotation the {@code ITestAnnotation} instance to be transformed.
     * @param testClass the class where the test method is located.
     * @param testConstructor the constructor of the test class.
     * @param testMethod the test method being transformed.
     */
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setDataProvider("getData");
        annotation.setDataProviderClass(Dataprovider.class);
        annotation.setRetryAnalyzer(Retryfailedtests.class);
    }
}