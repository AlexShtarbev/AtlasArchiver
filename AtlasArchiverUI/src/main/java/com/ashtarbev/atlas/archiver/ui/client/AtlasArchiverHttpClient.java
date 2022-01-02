package com.ashtarbev.atlas.archiver.ui.client;

import com.ashtarbev.atlas.archiver.core.item.Item;
import com.ashtarbev.atlas.archiver.core.item.StoredItemsPerUser;
import com.ashtarbev.atlas.archiver.ui.config.SpringClientConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

@Component
public class AtlasArchiverHttpClient {
    private final SpringClientConfig applicationConfig;
    private final ObjectMapper objectMapper;

    public AtlasArchiverHttpClient(
            SpringClientConfig applicationConfig,
            @Qualifier("atlasArchiverObjectMapper") ObjectMapper objectMapper
    ) {
        this.objectMapper = objectMapper;
        this.applicationConfig = applicationConfig;
    }

    public StoredItemsPerUser getAllItemsForUser(long userId) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/%s", getItemsUrl(), userId)))
                .header("Content-Type", "application/json")
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(stringBody -> {
                    try {
                        return objectMapper.readValue(stringBody, StoredItemsPerUser.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        return StoredItemsPerUser.builder().items(Collections.emptyList()).build();
                    }
                })
                .join();
    }

    private String getItemsUrl() {
        return String.format("%s/items", applicationConfig.getBaseUrl());
    }
}
