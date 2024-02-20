package org.code_generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.account.AccountService;
import org.utils.ReflectUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.*;

import static org.utils.Constants.*;

public class Generator {

    public void toGenerate() {
        try {
            String fileName = "template.ftl";
            File file = new File(fileName);
            String filePath = file.getAbsolutePath();
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
            cfg.setDirectoryForTemplateLoading(new File(filePath.replace(fileName, "")));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            Template template = cfg.getTemplate(fileName);

            TestClassInformation testClassInformation = new TestClassInformation();
            testClassInformation.setImports(List.of(
                    "org.account.Account",
                    "org.account.AccountService",
                    "org.account.AccountType",
                    "org.junit.jupiter.api.Test",
                    "org.mockito.ArgumentCaptor",
                    "org.user.User",
                    "java.math.BigDecimal",
                    "static org.mockito.Mockito.*"
            ));
            testClassInformation.setServices(List.of("AccountService"));
            testClassInformation.setTargetClass(AccountService.class);

            Class<?> targetClass = testClassInformation.getTargetClass();

            Method[] methods = targetClass.getMethods();
            List<TestMethodInformation> tests = Arrays.stream(methods).map(
                    method -> {
                        TestMethodInformation testMethodInformation = new TestMethodInformation();
                        testMethodInformation.setTestName(method.getName().substring(0, 1).toUpperCase()
                                + method.getName().substring(1));
                        List<Map<String, Object>> parameters = Arrays.stream(method.getParameters()).map(parameter -> {
                            Map<String, Object> param = new HashMap<>();
                            param.put("name", parameter.getName());
                            param.put("type", parameter.getType().getSimpleName());
                            param.put("defaultValue", ReflectUtil.getElementByTypeInList(DEFAULT_VALUE_TO_TEST,
                                    parameter.getType().getName()));
                            return param;
                        }).toList();
                        testMethodInformation.setParameters(parameters);
                        testMethodInformation.setExpectedResult(getExpectedResult(method.getName()));
                        testMethodInformation.setReturnType(method.getReturnType().getSimpleName());
                        return testMethodInformation;
                    }).filter(testMethodInformation -> Objects.nonNull(testMethodInformation.getParameters())
                    && !testMethodInformation.getParameters().isEmpty() && !OUT_SCOPE_METHODS.contains(
                            testMethodInformation.getTestName().toLowerCase())).toList();

            String classTestName = targetClass.getSimpleName() + "Test";

            Map<String, Object> data = new HashMap<>();
            data.put("tests", tests);
            data.put("className", classTestName);
            data.put("simpleName", targetClass.getSimpleName());
            data.put("imports", testClassInformation.getImports());
            Writer writer = new FileWriter("src/test/java/" + classTestName + ".java");
            template.process(data, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Optional<ExpectedResult> getExpectedResult(String methodName) {
        return EXPECTED_RESULTS.stream().filter(expectedResult -> expectedResult.getMethodName().equals(methodName))
                .findFirst();
    }

}
