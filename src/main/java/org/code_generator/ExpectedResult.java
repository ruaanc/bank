package org.code_generator;

public class ExpectedResult {

    private String methodName;
    private Object result;

    public ExpectedResult(String methodName, Object result) {
        this.methodName = methodName;
        this.result = result;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return String.format("new ExpectedResult(\"%s\", %s)", this.methodName, this.result);
    }
}
