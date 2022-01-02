package com.ashtarbev.atlas.archiver.ui.provider;

import com.ashtarbev.atlas.archiver.core.item.Item;
import com.ashtarbev.atlas.archiver.ui.client.AtlasArchiverHttpClient;
import com.vaadin.flow.data.provider.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
public class ItemsDataCallback implements CallbackDataProvider.FetchCallback<Item, Void>, CallbackDataProvider.CountCallback<Item, Void> {

    public static final long USER_ID = 721782112536854529L;
    private final AtlasArchiverHttpClient httpClient;

    private int size;

    public ItemsDataCallback(AtlasArchiverHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public Stream<Item> fetch(Query query) {
        // The index of the first item to load
        int offset = query.getOffset();

        // The number of items to load
        int limit = query.getLimit();

        List<Item> items = httpClient.getAllItemsForUser(USER_ID).getItems();
        size += items.size();
        return items.stream();
    }

    @Override
    public int count(Query<Item, Void> query) {
        return size;
    }
}
