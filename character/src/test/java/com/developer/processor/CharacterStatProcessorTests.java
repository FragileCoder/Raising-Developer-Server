package com.developer.processor;

import com.developer.domainmodel.CharacterDomain;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class CharacterStatProcessorTests {

    private CharacterStatProcessor processor;

    @TestFactory
    Stream<DynamicTest> updateStatSuccessTests() {
        processor = new CharacterStatProcessorImpl();
        List<List<Object>> values = List.of(
                List.of(new CharacterDomain(UUID.fromString("11b9649d-0ae3-4175-bf1f-abcfb1047100")), "backend", 10,
                        new CharacterDomain(UUID.fromString("11b9649d-0ae3-4175-bf1f-abcfb1047100"),10,0,0,0,0)),
                List.of(new CharacterDomain(UUID.fromString("21b9649d-0ae3-4175-bf1f-abcfb1047100")), "frontend", 7,
                        new CharacterDomain(UUID.fromString("21b9649d-0ae3-4175-bf1f-abcfb1047100"),0,7,0,0,0)),
                List.of(new CharacterDomain(UUID.fromString("31b9649d-0ae3-4175-bf1f-abcfb1047100")), "ops", 3,
                        new CharacterDomain(UUID.fromString("31b9649d-0ae3-4175-bf1f-abcfb1047100"),0,0,3,0,0)),
                List.of(new CharacterDomain(UUID.fromString("41b9649d-0ae3-4175-bf1f-abcfb1047100")), "health", -5,
                        new CharacterDomain(UUID.fromString("41b9649d-0ae3-4175-bf1f-abcfb1047100"),0,0,0,-5,0)),
                List.of(new CharacterDomain(UUID.fromString("51b9649d-0ae3-4175-bf1f-abcfb1047100")), "teamwork", -3,
                        new CharacterDomain(UUID.fromString("51b9649d-0ae3-4175-bf1f-abcfb1047100"),0,0,0,0,-3))
        );
        return values.stream().map(value ->
            DynamicTest.dynamicTest("CASE: 캐릭터의 특정 스텟을 업데이트한다.", () -> {
                Mono<CharacterDomain> result = processor.updateStat((CharacterDomain)value.get(0),(String)value.get(1), (int)value.get(2));
                StepVerifier.create(result)
                        .expectNext((CharacterDomain) value.get(3))
                        .verifyComplete();
            })
        );
    }
    @TestFactory
    Stream<DynamicTest> updateStatFailTests() {
        processor = new CharacterStatProcessorImpl();
        List<List<Object>> values = List.of(
                List.of(new CharacterDomain(UUID.fromString("11b9649d-0ae3-4175-bf1f-abcfb1047100")), "", 10,
                        new CharacterDomain(UUID.fromString("11b9649d-0ae3-4175-bf1f-abcfb1047100"),10,0,0,0,0)),
                List.of(new CharacterDomain(UUID.fromString("21b9649d-0ae3-4175-bf1f-abcfb1047100")), " ", 10,
                        new CharacterDomain(UUID.fromString("21b9649d-0ae3-4175-bf1f-abcfb1047100"),10,0,0,0,0)),
                List.of(new CharacterDomain(UUID.fromString("51b9649d-0ae3-4175-bf1f-abcfb1047100")), "unknown", -3,
                        new CharacterDomain(UUID.fromString("51b9649d-0ae3-4175-bf1f-abcfb1047100"),0,0,0,0,-3))
        );
        return values.stream().map(value ->
                DynamicTest.dynamicTest("CASE: 캐릭터 스텟 업데이트에 실패한다.", () -> {
                    Mono<CharacterDomain> result = processor.updateStat((CharacterDomain)value.get(0),(String)value.get(1), (int)value.get(2));
                    StepVerifier.create(result)
                            .expectErrorMatches(throwable -> throwable instanceof IllegalArgumentException
                                    && throwable.getMessage().equals("Unknown Stat Type"))
                            .verify();
                })
        );
    }
}
