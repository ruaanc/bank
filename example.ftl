<#list imports as import>
import ${import};
</#list>

class ${className} {

<#list services as service>
    private ${service} ${lower_case_first_letter(service)};
</#list>

    @BeforeEach
    void setUp() {
    <#list services as service>
        this.${lower_case_first_letter(service)} = new ${service}();
    </#list>
    }

    @Test
    void shouldInstantiateUser() {
        Account currentAccount = new Account(1000L, AccountType.CURRENT_ACCOUNT, new BigDecimal("1000.00"), false);
        User user01 = new User(1L, "User01", currentAccount);
        assertEquals(user01.getName(), "User01");
    }
}

<#function lower_case_first_letter str>
    <#if str?length != 0>
        <#return str?uncap_first>
    <#else>
        <#return ''>
    </#if>
</#function>
