package com.ashtarbev.atlas.archiver.core.item;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Data
@Builder
@Table("items")
public class Item {
  @Id
  long id;

  long userId;
  ItemType type;

  String url;
  String title;
  String imageUrl;

  Timestamp addedTimestamp;
}
