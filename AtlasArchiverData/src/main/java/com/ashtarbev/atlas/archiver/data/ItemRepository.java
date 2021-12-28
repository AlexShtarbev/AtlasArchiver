package com.ashtarbev.atlas.archiver.data;

import com.ashtarbev.atlas.archiver.core.item.Item;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    @Query("SELECT * FROM items ORDER BY id ASC")
    List<Item> getAllItems();

    @Query("SELECT * FROM items WHERE user_id = :userId ORDER BY id DESC")
    List<Item> getAllItemsForUser(@Param("userId") long userId);
}
