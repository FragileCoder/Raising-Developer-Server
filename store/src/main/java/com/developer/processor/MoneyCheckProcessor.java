package com.developer.processor;

import com.developer.domainmodel.CharacterDomain;
import reactor.core.publisher.Mono;

public interface MoneyCheckProcessor {
    Mono<Boolean> checkMoney(CharacterDomain characterDomain, int money);
}
