package org.norton.peterb.atm.test.action;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.norton.peterb.atm.action.ValidatePINAction;
import org.norton.peterb.atm.bean.CustomerAccountBean;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class ValidatePINActionTest {


    @ParameterizedTest
    @MethodSource("testArgs")
    void testCheckBalance(CustomerAccountBean customerAccountBean, String pin, boolean expectedResult) {
        Assertions.assertEquals(expectedResult,
                new ValidatePINAction().validatePIN(pin,customerAccountBean));
    }

    static Stream<Arguments> testArgs() {
        return Stream.of(
                Arguments.of(new CustomerAccountBean(
                                "70777077",
                                "ebff4481f8e655f086e196586bad484bbce2beabe139a07bf6e95b91f418751d",
                                BigDecimal.valueOf(1500L),BigDecimal.valueOf(0L))
                        ,"1234",true),
                Arguments.of(new CustomerAccountBean(
                                "70777077",
                                "3728f0a4591a43996bc6d85208e910299771c9e34a5f5b73d70673cca44b76ca",
                                BigDecimal.valueOf(1500L),BigDecimal.valueOf(200L))
                        ,"1234",false),
                Arguments.of(new CustomerAccountBean(
                                "70777077",
                                "ebff4481f8e655f086e196586bad484bbce2beabe139a07bf6e95b91f418751d",
                                BigDecimal.valueOf(1500L),BigDecimal.valueOf(0L))
                        ,"8888",false),
                Arguments.of(new CustomerAccountBean(
                                "70777077",
                                "3728f0a4591a43996bc6d85208e910299771c9e34a5f5b73d70673cca44b76ca",
                                BigDecimal.valueOf(1500L),BigDecimal.valueOf(200L))
                        ,"8888",true)
        );
    }

}
