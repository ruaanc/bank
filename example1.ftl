<#list imports as import>
import ${import};
</#list>

class ${className} {

    <#list tests as test>
    @Test
    void method${test.testName}ShouldBeExecutedSuccessfully() {
        ${simpleName} mock = spy(${simpleName}.class);

        mock.${lower_case_first_letter(test.testName)}(new User(1L, "User01", new Account(1000L, AccountType.CURRENT_ACCOUNT,
        new BigDecimal("1000.00"), false)), new BigDecimal("500.00"));

        verify(mock, times(1)).deposit(
            <#list test.parametersType as parameter>
            ArgumentCaptor.forClass(${parameter}.class).capture()<#if parameter_has_next>,</#if>
            </#list>
        );
    }
    </#list>
}

<#function lower_case_first_letter str>
    <#if str?length != 0>
        <#return str?uncap_first>
    <#else>
        <#return ''>
    </#if>
</#function>
