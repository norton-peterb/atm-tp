package org.norton.peterb.atm.test.store;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.norton.peterb.atm.exception.InsufficientCashException;
import org.norton.peterb.atm.exception.InvalidDenominationException;
import org.norton.peterb.atm.store.IMachineCashStore;
import org.norton.peterb.atm.store.MachineCashStore;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class MachineCashStoreTest {

    IMachineCashStore setupStore() {
        Map<BigDecimal,Integer> denominationLoad = new HashMap<>();
        denominationLoad.put(BigDecimal.valueOf(50L),10);
        denominationLoad.put(BigDecimal.valueOf(20L),30);
        denominationLoad.put(BigDecimal.valueOf(10L),30);
        denominationLoad.put(BigDecimal.valueOf(5L),20);
        return new MachineCashStore(denominationLoad);
    }

    @Test
    void testStoreTotal() {
        BigDecimal total = setupStore().getStoreTotal();
        Assertions.assertEquals(BigDecimal.valueOf(1500L),total);
    }

    @Test
    void testTotalWithWithdrawal() throws InvalidDenominationException, InsufficientCashException {
        IMachineCashStore machineCashStore = setupStore();
        machineCashStore.requestCash(BigDecimal.valueOf(50L));
        Assertions.assertEquals(BigDecimal.valueOf(1450L),machineCashStore.getStoreTotal());
    }

    @Test
    void testTotalWithWithdrawalTooMuch() {
        IMachineCashStore machineCashStore = setupStore();
        Assertions.assertThrows(InsufficientCashException.class,() ->
                machineCashStore.requestCash(BigDecimal.valueOf(1550L)));
        Assertions.assertEquals(BigDecimal.valueOf(1500L),machineCashStore.getStoreTotal());
    }

    @Test
    void testTotalWithWithdrawalTooMuchSecond() throws InvalidDenominationException, InsufficientCashException {
        IMachineCashStore machineCashStore = setupStore();
        machineCashStore.requestCash(BigDecimal.valueOf(100L));
        Assertions.assertThrows(InsufficientCashException.class,() ->
                machineCashStore.requestCash(BigDecimal.valueOf(1450L)));
        Assertions.assertEquals(BigDecimal.valueOf(1400L),machineCashStore.getStoreTotal());
    }

    @Test
    void testWithdrawalDenominationSingleFifty() throws InvalidDenominationException, InsufficientCashException {
        IMachineCashStore machineCashStore = setupStore();
        Map<BigDecimal,Integer> cash = machineCashStore.requestCash(BigDecimal.valueOf(50L));
        Assertions.assertEquals(1,cash.size());
        Assertions.assertNotNull(cash.get(BigDecimal.valueOf(50L)));
        Assertions.assertEquals(1,cash.get(BigDecimal.valueOf(50L)));
    }

    @Test
    void testWithdrawalDenominationSingleTwenty() throws InvalidDenominationException, InsufficientCashException {
        IMachineCashStore machineCashStore = setupStore();
        Map<BigDecimal,Integer> cash = machineCashStore.requestCash(BigDecimal.valueOf(20L));
        Assertions.assertEquals(1,cash.size());
        Assertions.assertNotNull(cash.get(BigDecimal.valueOf(20L)));
        Assertions.assertEquals(1,cash.get(BigDecimal.valueOf(20L)));
    }

    @Test
    void testWithdrawalDenominationSingleTen() throws InvalidDenominationException, InsufficientCashException {
        IMachineCashStore machineCashStore = setupStore();
        Map<BigDecimal,Integer> cash = machineCashStore.requestCash(BigDecimal.valueOf(10L));
        Assertions.assertEquals(1,cash.size());
        Assertions.assertNotNull(cash.get(BigDecimal.valueOf(10L)));
        Assertions.assertEquals(1,cash.get(BigDecimal.valueOf(10L)));
    }

    @Test
    void testWithdrawalDenominationSingleFive() throws InvalidDenominationException, InsufficientCashException {
        IMachineCashStore machineCashStore = setupStore();
        Map<BigDecimal,Integer> cash = machineCashStore.requestCash(BigDecimal.valueOf(5L));
        Assertions.assertEquals(1,cash.size());
        Assertions.assertNotNull(cash.get(BigDecimal.valueOf(5L)));
        Assertions.assertEquals(1,cash.get(BigDecimal.valueOf(5L)));
    }

    @Test
    void testWithdrawalDenominationSeventy() throws InvalidDenominationException, InsufficientCashException {
        IMachineCashStore machineCashStore = setupStore();
        Map<BigDecimal,Integer> cash = machineCashStore.requestCash(BigDecimal.valueOf(70L));
        Assertions.assertEquals(2,cash.size());
        Assertions.assertNotNull(cash.get(BigDecimal.valueOf(50L)));
        Assertions.assertNotNull(cash.get(BigDecimal.valueOf(20L)));
        Assertions.assertEquals(1,cash.get(BigDecimal.valueOf(50L)));
        Assertions.assertEquals(1,cash.get(BigDecimal.valueOf(20L)));
    }

    @Test
    void testWithdrawalDenominationOneSeventy() throws InvalidDenominationException, InsufficientCashException {
        IMachineCashStore machineCashStore = setupStore();
        Map<BigDecimal,Integer> cash = machineCashStore.requestCash(BigDecimal.valueOf(170L));
        Assertions.assertEquals(2,cash.size());
        Assertions.assertNotNull(cash.get(BigDecimal.valueOf(50L)));
        Assertions.assertNotNull(cash.get(BigDecimal.valueOf(20L)));
        Assertions.assertEquals(3,cash.get(BigDecimal.valueOf(50L)));
        Assertions.assertEquals(1,cash.get(BigDecimal.valueOf(20L)));
    }

    @ParameterizedTest
    @ValueSource(longs = {1L,2L,3L,4L,6L,7L,8L,9L,11L,17L,26L,101L,171L})
    void testWithdrawalInvalidDenominations(Long value) {
        IMachineCashStore machineCashStore = setupStore();
        Assertions.assertThrows(InvalidDenominationException.class,() ->
                machineCashStore.requestCash(BigDecimal.valueOf(value)));
    }

    @Test
    void testCashStoreReturnFunds() throws InvalidDenominationException, InsufficientCashException{
        IMachineCashStore machineCashStore = setupStore();
        Map<BigDecimal,Integer> cash = machineCashStore.requestCash(BigDecimal.valueOf(70L));
        Assertions.assertEquals(BigDecimal.valueOf(1430L),machineCashStore.getStoreTotal());
        machineCashStore.returnCash(cash);
        Assertions.assertEquals(BigDecimal.valueOf(1500L),machineCashStore.getStoreTotal());
    }
}
