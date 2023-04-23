package com.developer.service;

import com.developer.domainmodel.CharacterDomain;
import com.developer.domainmodel.ItemDomain;
import com.developer.processor.ItemAddProcessor;
import com.developer.processor.MoneyCheckProcessor;
import com.developer.processor.MoneySpendProcessor;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class StoreServiceImpl implements StoreService{

    private final ItemAddProcessor itemAddProcessor;
    private final MoneyCheckProcessor moneyCheckProcessor;
    private final MoneySpendProcessor moneySpendProcessor;

    public StoreServiceImpl(ItemAddProcessor itemAddProcessor, MoneyCheckProcessor moneyCheckProcessor, MoneySpendProcessor moneySpendProcessor) {
        this.itemAddProcessor = itemAddProcessor;
        this.moneyCheckProcessor = moneyCheckProcessor;
        this.moneySpendProcessor = moneySpendProcessor;
    }

    @Override
    public Mono<Boolean> purchasingItem(UUID characterUUID, UUID itemUUID) {

        moneyCheckProcessor.checkMoney(new CharacterDomain(UUID.randomUUID()), 0);
        moneySpendProcessor.spendMoney(new CharacterDomain(UUID.randomUUID()), 0);
        itemAddProcessor.addItem(new CharacterDomain(UUID.randomUUID()), new ItemDomain());
        return Mono.just(Boolean.TRUE);
    }
}
