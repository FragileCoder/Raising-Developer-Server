package com.developer.processor;

import com.developer.domainmodel.CharacterDomain;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class MoneyCheckProcessorTests {

    private MoneyCheckProcessor processor;

    @TestFactory
    Stream<DynamicTest> checkMoneySuccessTests() {
        processor = new MoneyCheckProcessorImpl();

        List<List<Object>> values = List.of(
                List.of(new CharacterDomain(UUID.fromString("11b9649d-0ae3-4175-bf1f-abcfb1047100"),
                                0, 0, 0, 0, 0, 1000),
                        1000, true),
                List.of(new CharacterDomain(UUID.fromString("21b9649d-0ae3-4175-bf1f-abcfb1047100"),
                                0, 0, 0, 0, 0, 2300),
                        500, true),
                List.of(new CharacterDomain(UUID.fromString("31b9649d-0ae3-4175-bf1f-abcfb1047100"),
                                0, 0, 0, 0, 0, 300),
                        1000, false),
                List.of(new CharacterDomain(UUID.fromString("41b9649d-0ae3-4175-bf1f-abcfb1047100"),
                                0, 0, 0, 0, 0, 0),
                        700, false)
        );

        return values.stream().map(value ->
                DynamicTest.dynamicTest("CASE: 보유하고 있는 금액 체크에 성공한다.", () -> {
                    Mono<Boolean> result = processor.checkMoney((CharacterDomain) value.get(0), (int) value.get(1));
                    StepVerifier.create(result)
                            .expectNext((Boolean) value.get(2))
                            .verifyComplete();
                })
        );
    }
}