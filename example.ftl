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
    void shouldInstantiate${classTarget}() {
        ${classTarget} ${lower_case_first_letter(classTarget)} = ${objectInstance};
        assertNotEquals(null, ${lower_case_first_letter(classTarget)});
    }

    @Test
    void methodShouldBeExecutedSuccessfully() {
        AccountService mock = spy(AccountService.class);

        mock.deposit(new User(1L, "User01", new Account(1000L, AccountType.CURRENT_ACCOUNT,
        new BigDecimal("1000.00"), false)), new BigDecimal("500.00"));

        verify(mock, times(1)).deposit(ArgumentCaptor.forClass(User.class).capture(),
        ArgumentCaptor.forClass(BigDecimal.class).capture());

        Method[] methods = mock.getClass().getMethods();

        String methodName = "deposit";

        Method method = Arrays.stream(methods)
        .filter(m -> m.getName().equals(methodName))
        .findFirst()
        .orElseThrow(() -> new NoSuchMethodError(methodName));

        List<String> parametersTypes = Arrays.stream(method.getParameters()).map(Parameter::getType).map(Class::getName).toList();

        System.out.println(parametersTypes);

    }
}

<#function lower_case_first_letter str>
    <#if str?length != 0>
        <#return str?uncap_first>
    <#else>
        <#return ''>
    </#if>
</#function>
