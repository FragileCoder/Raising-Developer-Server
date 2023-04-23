package com.developer.processor;

import com.developer.domainmodel.CharacterDomain;
import reactor.core.publisher.Mono;

public class MoneyCheckProcessorImpl implements MoneyCheckProcessor{
    @Override
    public Mono<Boolean> checkMoney(CharacterDomain characterDomain, int money) {
        if (characterDomain.money() >= money) return Mono.just(true);
        else return Mono.just(false);
    }
}
