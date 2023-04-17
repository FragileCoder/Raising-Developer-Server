package com.developer.service;

import com.developer.domainmodel.CharacterDomain;
import com.developer.processor.BookFindProcessorImpl;
import com.developer.processor.CharacterStatProcessorImpl;
import com.developer.processor.InventoryBookProcessorImpl;
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
class CharacterServiceTest {

    @InjectMocks
    private CharacterServiceImpl characterService;

    @Mock
    private CharacterStatProcessorImpl characterStatProcessor;

    @Mock
    private BookFindProcessorImpl bookFindProcessor;

    @Mock
    private InventoryBookProcessorImpl inventoryBookProcessor;

    @Test
    @DisplayName("readBook 테스트")
    void readBookTest() {
        //given
        //특정 책이 들어온다.
        String characterUUID = "testCharacterUuid";
        String bookUUID = "testBookUuid";

        //when
        //캐릭터가 해당 책을 읽는다.
        Mono<Boolean> result = characterService.readBook(characterUUID, bookUUID);

        //then
        //캐릭터의 인벤토리에 입력받은 책이 존재하는지 확인한다.
        //UUID를 통해 책을 조회한다.
        //캐릭터의 인벤토리에서 책을 소멸시킨다.
        //조회한 책에 해당하는 스탯을 올려준다.
        //적용 결과를 알려준다.
        then(inventoryBookProcessor).should(times(1)).checkBook(anyString(), anyString());
        then(bookFindProcessor).should(times(1)).getBook(anyString());
        then(inventoryBookProcessor).should(times(1)).consumeBook(anyString(), anyString());
        then(characterStatProcessor).should(times(1)).updateStat(new CharacterDomain(UUID.randomUUID()), "backend", 3);
        assertThat(result).isInstanceOf(Mono.class); // todo: Mono 검증방법 구상 : chatGPT의 구상은 해당 두줄로 구성
        assertThat(result.block()).isInstanceOf(Boolean.class);

    }

}
