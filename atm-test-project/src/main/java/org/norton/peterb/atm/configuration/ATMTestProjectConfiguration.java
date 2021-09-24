package org.norton.peterb.atm.configuration;

import org.norton.peterb.atm.bean.CustomerAccountBean;
import org.norton.peterb.atm.store.AccountStore;
import org.norton.peterb.atm.store.MachineCashStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ATMTestProjectConfiguration {

    @Bean
    public AccountStore accountStore() {
        Map<String, CustomerAccountBean> accountBeanMap = new HashMap<>();
        accountBeanMap.put("123456789",
                new CustomerAccountBean("123456789",
                "4a293d694a6dfbfe6d12905e399ba9bf55a542d977c9b20218260f5c6828e259",
                BigDecimal.valueOf(800L),BigDecimal.valueOf(200L)));
        accountBeanMap.put("987654321",
                new CustomerAccountBean("987654321",
                        "d3e3353dc5c3a44c334c756f7a23933aaf92681b73e74629f61cc1d79a16895a",
                        BigDecimal.valueOf(1230L),BigDecimal.valueOf(150L)));
        return new AccountStore(accountBeanMap);
    }

    @Bean
    public MachineCashStore machineCashStore() {
        Map<BigDecimal,Integer> denominationLoad = new HashMap<>();
        denominationLoad.put(BigDecimal.valueOf(50L),10);
        denominationLoad.put(BigDecimal.valueOf(20L),30);
        denominationLoad.put(BigDecimal.valueOf(10L),30);
        denominationLoad.put(BigDecimal.valueOf(5L),20);
        return new MachineCashStore(denominationLoad);
    }
}
