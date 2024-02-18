package org.code_generator;

import java.util.List;
import java.util.Map;

public class TestMethodInformation {

    private String testName;
    private List<Map<String, Object>> parameters;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public List<Map<String, Object>> getParameters() {
        return parameters;
    }

    public void setParameters(List<Map<String, Object>> parameters) {
        this.parameters = parameters;
    }
}
