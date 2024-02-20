package org.utils;

import org.account.Account;
import org.account.AccountType;
import org.code_generator.ExpectedResult;
import org.user.User;

import java.math.BigDecimal;
import java.util.List;

public class Constants {

    private Constants() {}

    // TODO: This data needs to be passed by parameter.
    public static final List<?> DEFAULT_VALUE_TO_TEST = List.of(
            "Test",
            1L,
            1.0,
            1,
            new BigDecimal("500.00"),
            new User(1L, "User01", new Account(1000L, AccountType.CURRENT_ACCOUNT,
                    new BigDecimal("1000.00"), false)));

    // TODO: This data needs to be passed by parameter.
    public static final List<ExpectedResult> EXPECTED_RESULTS = List.of(new ExpectedResult("transfer",
            BigDecimal.valueOf(500.0)), new ExpectedResult("checkBalance", BigDecimal.valueOf(1000.0)),
            new ExpectedResult("deposit", BigDecimal.valueOf(1500.0)));

    public static final List<String> PRIMITIVE_TYPES = List.of("byte", "short", "int", "long", "float", "double", "char",
            "boolean");

    public static final List<String> OUT_SCOPE_METHODS = List.of("wait", "equals");

}
