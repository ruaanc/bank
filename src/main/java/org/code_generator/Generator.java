package org.code_generator;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;
import org.utils.StringUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Generator {

    public void toGenerate() {
        String fileName = "JavaClass.stg";
        File file = new File(fileName);
        String filePath = file.getAbsolutePath();
        STGroup group = new STGroupFile(filePath);
        ST template = group.getInstanceOf("classTemplate");

        TestClassInformation testClassInformation = new TestClassInformation();
        testClassInformation.setName("UserTest");
        testClassInformation.setServices(List.of("AccountService"));

        template.add("testClassInformation", testClassInformation);
        template.add("util", new StringUtil());
        String generatedCode = template.render();
        File fileToWrite = new File("src/test/java/UserTest.java");
        String fileWritePath = fileToWrite.getAbsolutePath();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileWritePath))) {
            writer.write(generatedCode);
            System.out.println("Java file generated successfully: " + fileWritePath);
        } catch (IOException e) {
            System.err.println("Error writing Java file: " + e.getMessage());
        }
    }

}
