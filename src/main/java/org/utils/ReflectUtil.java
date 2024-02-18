package org.utils;

import java.util.List;

import static org.utils.Constants.PRIMITIVE_TYPES;

public class ReflectUtil {

    private ReflectUtil() {}

    public static String getQualifiedName(String simpleName) throws ClassNotFoundException {
        Package[] pacotes = Package.getPackages();

        for (Package pack : pacotes) {
            String qualifiedName = pack.getName() + "." + simpleName;

            try {
                Class.forName(qualifiedName);
                return qualifiedName;
            } catch (ClassNotFoundException e) {
                // Class not found in this package, keep looking
            }
        }

        throw new ClassNotFoundException("Class not found for simple name: " + simpleName);
    }

    public static Object getElementByTypeInList(List<?> list, String qualifiedName) {
        for (Object obj : list) {
            try {
                if(isPrimitiveType(qualifiedName)) {
                    qualifiedName = getQualifiedName(primitiveToWrapperClasses(qualifiedName));
                }
                if (Class.forName(qualifiedName).isInstance(obj)) {
                    return obj;
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public static boolean isPrimitiveType(String qualifiedName) {
        return PRIMITIVE_TYPES.contains(qualifiedName);
    }

    public static String primitiveToWrapperClasses(String type) {
        return switch (type) {
            case "byte" -> "Byte";
            case "short" -> "Short";
            case "int" -> "Integer";
            case "long" -> "Long";
            case "float" -> "Float";
            case "double" -> "Double";
            case "char" -> "Character";
            case "boolean" -> "Boolean";
            default -> "";
        };
    }
}
