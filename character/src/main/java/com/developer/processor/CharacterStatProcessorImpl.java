package com.developer.processor;

import com.developer.domainmodel.CharacterDomain;
import reactor.core.publisher.Mono;

public class CharacterStatProcessorImpl implements CharacterStatProcessor{

    @Override
    public Mono<CharacterDomain> updateStat(CharacterDomain character, String targetStat, int point) {
        return Mono.just(character)
                .map(c -> switch (targetStat) {
                    case "backend" -> c.withBackend(c.backend() + point);
                    case "frontend" -> c.withFrontend(c.frontend() + point);
                    case "ops" -> c.withOps(c.ops() + point);
                    case "health" -> c.withHealth(c.health() + point);
                    case "teamwork" -> c.withTeamwork(c.teamwork() + point);
                    default -> throw new IllegalArgumentException("Unknown Stat Type");
                });
    }
}
