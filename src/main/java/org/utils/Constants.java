package org.utils;

import org.account.Account;
import org.account.AccountType;
import org.user.User;

import java.math.BigDecimal;
import java.util.List;

public class Constants {
    public static List<?> DEFAULT_VALUE_TO_TEST = List.of(
            "Test",
            1L,
            1.0,
            1,
            new BigDecimal("500.00"),
            new User(1L, "User01", new Account(1000L, AccountType.CURRENT_ACCOUNT,
                    new BigDecimal("1000.00"), false)));
    public static List<String> PRIMITIVE_TYPES = List.of("byte", "short", "int", "long", "float", "double", "char",
            "boolean");

    public static List<String> OUT_SCOPE_METHODS = List.of("wait", "equals");

}
