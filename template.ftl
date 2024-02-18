<#list imports as import>
import ${import};
</#list>

class ${className} {

    <#list tests as test>
    @Test
    void method${test.testName}ShouldBeExecutedSuccessfully() {
        ${simpleName} mock = spy(${simpleName}.class);

        mock.${lower_case_first_letter(test.testName)}(
            <#list test.parameters as parameter>
            ${parameter.defaultValue}<#if parameter_has_next>,</#if>
            </#list>
        );

        verify(mock, times(1)).${lower_case_first_letter(test.testName)}(
            <#list test.parameters as parameter>
            ArgumentCaptor.forClass(${parameter.type}.class).capture()<#if parameter_has_next>,</#if>
            </#list>
        );
    }

    @Test
    void method${test.testName}NotBeExecutedUnsuccessfully() {
        ${simpleName} mock = spy(${simpleName}.class);

        verify(mock, never()).${lower_case_first_letter(test.testName)}(
        <#list test.parameters as parameter>
            ArgumentCaptor.forClass(${parameter.type}.class).capture()<#if parameter_has_next>,</#if>
        </#list>
        );
    }
    <#if test.expectedResult.isPresent()>
    @Test
    void method${test.testName}ShouldReturnExpectedResult() {
        ${simpleName} mock = spy(${simpleName}.class);

        BigDecimal response = mock.${lower_case_first_letter(test.testName)}(
        <#list test.parameters as parameter>
            ${parameter.defaultValue}<#if parameter_has_next>,</#if>
        </#list>
        );

        Assertions.assertEquals(${test.expectedResult.get()}.getResult(), response);
    }

        </#if>
    </#list>

}

<#function lower_case_first_letter str>
    <#if str?length != 0>
        <#return str?uncap_first>
    <#else>
        <#return ''>
    </#if>
</#function>
