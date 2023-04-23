package com.developer.processor;

import com.developer.domainmodel.CharacterDomain;
import reactor.core.publisher.Mono;

public interface MoneySpendProcessor {
    Mono<CharacterDomain> spendMoney(CharacterDomain characterDomain, int money);
}
