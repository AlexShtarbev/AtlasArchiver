package com.ashtarbev.atlas.archiver.service.item;

import com.ashtarbev.atlas.archiver.core.item.*;
import com.ashtarbev.atlas.archiver.data.ItemRepository;
import com.ashtarbev.atlas.archiver.service.item.exceptions.ImageMetadataExtractionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
  private static final Logger LOG =  LogManager.getLogger(ItemController.class);
  private final UrlMetadataExtractor urlMetadataExtractor;
  private final ItemRepository itemRepository;

  public ItemController(UrlMetadataExtractor urlMetadataExtractor, ItemRepository itemRepository) {
    this.urlMetadataExtractor = urlMetadataExtractor;
    this.itemRepository = itemRepository;
  }

  @PostMapping("")
  public HttpEntity<Item> storeItem(@RequestBody StoreItemRequest request) {
    try {
      ItemMetadata metadata = urlMetadataExtractor.extract(request.getUrl());
      Item item = Item.builder()
              .userId(request.getUserId())
              .type(ItemType.ARTICLE)
              .url(metadata.getUrl())
              .title(metadata.getTitle())
              .imageUrl(metadata.getImageUrl())
              .addedTimestamp(Timestamp.from(Instant.now()))
              .build();
      Item savedItem = itemRepository.save(item);
      return ResponseEntity.ok().body(savedItem);
    } catch (ImageMetadataExtractionException e) {
      LOG.error(e);
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping("/{userId}")
  public HttpEntity<StoredItemsPerUser> getStoredItemsPerUser(@PathVariable long userId) {
    List<Item> allItems = itemRepository.getAllItemsForUser(userId);
    StoredItemsPerUser storedItemsPerUser = StoredItemsPerUser.builder()
            .items(allItems)
            .build();

    return ResponseEntity.ok().body(storedItemsPerUser);
  }

  @GetMapping("parse")
  public HttpEntity<ItemMetadata> parse(@RequestParam("url") String url) {
    try {
      ItemMetadata metadata = urlMetadataExtractor.extract(url);
      return ResponseEntity.ok().body(metadata);
    } catch (ImageMetadataExtractionException e) {
      LOG.error(e);
      return ResponseEntity.internalServerError().build();
    }
  }
}
