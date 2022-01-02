package com.ashtarbev.atlas.archiver.core.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@Table("items")
public class Item {
  @Id
  long id;

  long userId;
  ItemType type;

  String url;
  String title;
  String imageUrl;

  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS+HH:mm")
  Timestamp addedTimestamp;
}
