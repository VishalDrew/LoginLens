package com.field.Listeners;

import com.field.Constants.Constants;
import com.field.Exceptions.InvalidpathforExcelException;
import com.field.Utils.Excelutils;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * The {@code MethodInterceptor} class implements the {@code IMethodInterceptor} interface to provide custom
 * behavior for test method selection and configuration.
 * <p>This class reads test configuration from an Excel file and uses this configuration to filter and configure
 * test methods before execution.</p>
 * 
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
public class MethodInterceptor implements IMethodInterceptor {

    /**
     * Intercepts and modifies the list of test methods based on the configuration from an Excel file.
     * <p>This method reads the test configurations, such as test description, invocation count, and priority, from
     * an Excel sheet and applies these configurations to the test methods. Only methods flagged for execution are included.</p>
     * 
     * @param methods the list of {@code IMethodInstance} instances representing the test methods.
     * @param context the {@code ITestContext} instance providing context for the test methods.
     * @return a list of {@code IMethodInstance} instances representing the filtered and configured test methods.
     * @throws InvalidpathforExcelException if the Excel file containing test configurations is not found.
     */
    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        List<Map<String, String>> list = null;
        try {
            list = Excelutils.getTestDetails(Constants.getRunManagersheet());
        } catch (IOException e) {
            throw new InvalidpathforExcelException("Excel File you trying to read is Not Found -->" + e);
        }
        List<IMethodInstance> result = new ArrayList<>();
        for (int i = 0; i < methods.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (methods.get(i).getMethod().getMethodName().equalsIgnoreCase(list.get(j).get("Testname")) &&
                        list.get(j).get("Execute").equalsIgnoreCase("yes")) {
                    methods.get(i).getMethod().setDescription(list.get(j).get("Test Description"));
                    methods.get(i).getMethod().setInvocationCount(Integer.parseInt(list.get(j).get("Count")));
                    methods.get(i).getMethod().setPriority(Integer.parseInt(list.get(j).get("Priority")));
                    result.add(methods.get(i));
                }
            }
        }
        return result;
    }
}


