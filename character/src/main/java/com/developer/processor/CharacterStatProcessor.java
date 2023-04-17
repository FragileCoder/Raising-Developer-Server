package com.developer.processor;

import com.developer.domainmodel.CharacterDomain;
import reactor.core.publisher.Mono;


public interface CharacterStatProcessor {
    Mono<CharacterDomain> updateStat(CharacterDomain character, String targetStat, int point);
}
