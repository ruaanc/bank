package org.code_generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Generator {

    public void toGenerate() {
        try {
            String fileName = "example.ftl";
            File file = new File(fileName);
            String filePath = file.getAbsolutePath();
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
            cfg.setDirectoryForTemplateLoading(new File(filePath.replace(fileName, "")));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            Template template = cfg.getTemplate(fileName);

            TestClassInformation testClassInformation = new TestClassInformation();
            testClassInformation.setName("GenerateTest");
            testClassInformation.setImports(List.of(
                    "org.account.Account",
                    "org.account.AccountService",
                    "org.account.AccountType",
                    "org.junit.jupiter.api.BeforeEach",
                    "org.junit.jupiter.api.Test",
                    "org.user.User",
                    "java.math.BigDecimal",
                    "static org.junit.jupiter.api.Assertions.assertEquals"
            ));
            testClassInformation.setServices(List.of("AccountService"));

            Map<String, Object> data = new HashMap<>();
            data.put("className", testClassInformation.getName());
            data.put("services", testClassInformation.getServices());
            data.put("imports", testClassInformation.getImports());
            Writer writer = new FileWriter("src/test/java/" + testClassInformation.getName() + ".java");
            template.process(data, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
