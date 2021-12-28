package com.ashtarbev.atlas.archiver.core.item;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreItemRequest {
    long userId;
    String url;
}
