package org.utils;

public class StringUtil {

    public static String toCamelCase(String input) {
        String[] words = input.split("[\\W_]+");
        if (words.length == 0) {
            return "";
        }
        StringBuilder camelCase = new StringBuilder(words[0].toLowerCase());
        for (int i = 1; i < words.length; i++) {
            String capitalizedWord = capitalizeFirstLetter(words[i]);
            camelCase.append(capitalizedWord);
        }
        return camelCase.toString();
    }

    private static String capitalizeFirstLetter(String word) {
        return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
    }

}
