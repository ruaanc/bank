package org.code_generator;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Generator {

    public void toGenerate() {
        String fileName = "JavaClass.stg";
        File file = new File(fileName);
        String filePath = file.getAbsolutePath();
        STGroup group = new STGroupFile(filePath);
        ST template = group.getInstanceOf("classTemplate");
        template.add("className", "UserTest");
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
