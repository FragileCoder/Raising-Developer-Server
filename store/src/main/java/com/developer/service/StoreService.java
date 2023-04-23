package com.developer.service;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface StoreService {
    Mono<Boolean> purchasingItem(UUID characterUUID, UUID itemUUID);
}
