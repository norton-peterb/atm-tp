package org.norton.peterb.atm.test.action;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.norton.peterb.atm.action.CheckBalanceAvailableAction;
import org.norton.peterb.atm.bean.AvailableBalanceBean;
import org.norton.peterb.atm.bean.CustomerAccountBean;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class CheckBalanceAvailableActionTest {

    @ParameterizedTest
    @MethodSource("testArgs")
    void testCheckBalance(CustomerAccountBean customerAccountBean, BigDecimal expectedBalance,
                          BigDecimal expectedAvailable) {
        AvailableBalanceBean availableBalanceBean =
                new CheckBalanceAvailableAction().checkAvailableBalance(customerAccountBean);
        Assertions.assertEquals(expectedBalance,availableBalanceBean.getAccountBalance());
        Assertions.assertEquals(expectedAvailable,availableBalanceBean.getAvailableBalance());
    }

    static Stream<Arguments> testArgs() {
        return Stream.of(
                Arguments.of(new CustomerAccountBean(
                        "TEST",
                        "TEST",BigDecimal.valueOf(1500L),BigDecimal.valueOf(0L)),
                        BigDecimal.valueOf(1500L),BigDecimal.valueOf(1500L)),
                Arguments.of(new CustomerAccountBean(
                                "TEST",
                                "TEST",BigDecimal.valueOf(1500L),BigDecimal.valueOf(200L)),
                        BigDecimal.valueOf(1500L),BigDecimal.valueOf(1700L))
        );
    }
}
