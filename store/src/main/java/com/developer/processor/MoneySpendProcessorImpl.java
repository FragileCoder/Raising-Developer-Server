package com.developer.processor;

import com.developer.domainmodel.CharacterDomain;
import reactor.core.publisher.Mono;

public class MoneySpendProcessorImpl implements MoneySpendProcessor{
    @Override
    public Mono<CharacterDomain> spendMoney(CharacterDomain characterDomain, int money) {
        return Mono.just(characterDomain.withMoney(characterDomain.money() - money));
    }
}
