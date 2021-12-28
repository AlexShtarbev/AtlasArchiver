package com.ashtarbev.atlas.archiver.core.item;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ItemMetadata {
    String url;
    String title;
    String imageUrl;
}
