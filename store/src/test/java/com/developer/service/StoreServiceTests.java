package com.developer.service;


import com.developer.domainmodel.CharacterDomain;
import com.developer.domainmodel.ItemDomain;
import com.developer.processor.ItemAddProcessorImpl;
import com.developer.processor.MoneyCheckProcessorImpl;
import com.developer.processor.MoneySpendProcessorImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class StoreServiceTests {

    @InjectMocks
    private StoreServiceImpl service;

    @Mock
    private MoneyCheckProcessorImpl moneyCheckProcessor;

    @Mock
    private MoneySpendProcessorImpl moneySpendProcessor;

    @Mock
    private ItemAddProcessorImpl itemAddProcessor;

    @Test
    @DisplayName("CASE: StoreService purchasingItem Test")
    void purchasingItemTest() {
//        수중에 있는 금액을 확인합니다.
//        사용 가능한 자금에서 구매 금액을 뺍니다.
//        구매한 아이템을 캐릭터의 인벤토리에 추가합니다.
        UUID userUUID = UUID.randomUUID();
        UUID itemUUID = UUID.randomUUID();
        UUID characterUUID = UUID.randomUUID();
        Mono<Boolean> result = service.purchasingItem(characterUUID, itemUUID);

        then(moneyCheckProcessor).should(times(1)).checkMoney(any(CharacterDomain.class), anyInt());
        then(moneySpendProcessor).should(times(1)).spendMoney(any(CharacterDomain.class), anyInt());
        then(itemAddProcessor).should(times(1)).addItem(any(CharacterDomain.class), any(ItemDomain.class));

        assertThat(result).isInstanceOf(Mono.class);
        assertThat(result.block()).isInstanceOf(Boolean.class);
    }
}
