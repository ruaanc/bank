package org.code_generator;

import java.util.List;

public class TestClassInformation {
    private Class<?> targetClass;
    private String objectInstance;
    private List<String> services;
    private List<String> imports;

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }

    public List<String> getImports() {
        return imports;
    }

    public void setImports(List<String> imports) {
        this.imports = imports;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    public String getObjectInstance() {
        return objectInstance;
    }

    public void setObjectInstance(String objectInstance) {
        this.objectInstance = objectInstance;
    }
}
