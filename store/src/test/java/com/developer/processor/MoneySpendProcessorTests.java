package com.developer.processor;

import com.developer.domainmodel.CharacterDomain;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class MoneySpendProcessorTests {

    private MoneySpendProcessor processor;


    @TestFactory
    Stream<DynamicTest> checkMoneySuccessTests() {
        processor = new MoneySpendProcessorImpl();

        List<List<Object>> values = List.of(
                List.of(new CharacterDomain(UUID.fromString("11b9649d-0ae3-4175-bf1f-abcfb1047100"),
                                0, 0, 0, 0, 0, 1000),
                        1000,
                        new CharacterDomain(UUID.fromString("11b9649d-0ae3-4175-bf1f-abcfb1047100"),
                                0, 0, 0, 0, 0, 0)),
                List.of(new CharacterDomain(UUID.fromString("21b9649d-0ae3-4175-bf1f-abcfb1047100"),
                                0, 0, 0, 0, 0, 2300),
                        500,
                        new CharacterDomain(UUID.fromString("21b9649d-0ae3-4175-bf1f-abcfb1047100"),
                                0, 0, 0, 0, 0, 2300-500)),
                List.of(new CharacterDomain(UUID.fromString("31b9649d-0ae3-4175-bf1f-abcfb1047100"),
                                0, 0, 0, 0, 0, 300),
                        1000,
                        new CharacterDomain(UUID.fromString("31b9649d-0ae3-4175-bf1f-abcfb1047100"),
                                0, 0, 0, 0, 0, 300-1000)),
                List.of(new CharacterDomain(UUID.fromString("41b9649d-0ae3-4175-bf1f-abcfb1047100"),
                                0, 0, 0, 0, 0, 0),
                        700,
                        new CharacterDomain(UUID.fromString("41b9649d-0ae3-4175-bf1f-abcfb1047100"),
                                0, 0, 0, 0, 0, -700))
        );


        return values.stream().map(value ->
                DynamicTest.dynamicTest("CASE: 캐릭터의 보유금액을 차감시킨다.", () -> {
                    Mono<CharacterDomain> result = processor.spendMoney((CharacterDomain) value.get(0), (int) value.get(1));
                    StepVerifier.create(result)
                            .expectNext((CharacterDomain) value.get(2))
                            .verifyComplete();
                })
        );
    }
}
