package com.ashtarbev.atlas.archiver.service.item;

import java.io.IOException;

import com.ashtarbev.atlas.archiver.core.item.ItemMetadata;
import com.ashtarbev.atlas.archiver.service.item.exceptions.ImageMetadataExtractionException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class UrlMetadataExtractor {
  public ItemMetadata extract(String url) throws ImageMetadataExtractionException {
    try {
      Document doc = Jsoup.connect(url).get();
      System.out.println(doc.title());
      Elements metaTags = doc.getElementsByTag("meta");

      ItemMetadata.ItemMetadataBuilder builder = ItemMetadata.builder();
//      builder.url(url);
      for (Element metaTag : metaTags) {
        String property = metaTag.attr("property");
        String content = metaTag.attr("content");
        if ("og:title".equals(property)) {
          builder.title(content);
        } else if ("og:image".equals(property)) {
          builder.imageUrl(content);
        } else if ("og:url".equals(property)) {
          builder.url(content);
        }
      }

      return builder.build();
    } catch (IOException e) {
      throw new ImageMetadataExtractionException(e);
    }
  }
}
