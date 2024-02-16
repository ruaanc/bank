package org.code_generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.account.AccountService;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Generator {

    public void toGenerate() {
        try {
            String fileName = "example1.ftl";
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
                    "org.junit.jupiter.api.BeforeEach",
                    "org.junit.jupiter.api.Test",
                    "org.user.User",
                    "java.math.BigDecimal",
                    "static org.junit.jupiter.api.Assertions.assertNotEquals"
            ));
            testClassInformation.setServices(List.of("AccountService"));
            testClassInformation.setTargetClass(AccountService.class);
            testClassInformation.setObjectInstance("new User(1L, \"User01\", new Account(1000L, AccountType.CURRENT_ACCOUNT, new BigDecimal(\"1000.00\"), false))");

            Class<?> targetClass = testClassInformation.getTargetClass();

            Method[] methods = targetClass.getMethods();
            List<TestMethodInformation> tests = Arrays.stream(methods).map(
                    method -> {
                        TestMethodInformation testMethodInformation = new TestMethodInformation();
                        testMethodInformation.setTestName(method.getName().substring(0, 1).toUpperCase()
                                + method.getName().substring(1));
                        testMethodInformation.setParametersType(Arrays.stream(method.getParameterTypes()).map(
                                aClass -> aClass.getName().substring(aClass.getName().lastIndexOf(".") + 1))
                                .toList());
                        return testMethodInformation;
                    }).filter(testMethodInformation -> !testMethodInformation.getParametersType().isEmpty()).toList();

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

}
