package org.code_generator;

import java.util.List;

public class TestMethodInformation {

    private String testName;
    private List<String> parametersType;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public List<String> getParametersType() {
        return parametersType;
    }

    public void setParametersType(List<String> parametersType) {
        this.parametersType = parametersType;
    }
}
