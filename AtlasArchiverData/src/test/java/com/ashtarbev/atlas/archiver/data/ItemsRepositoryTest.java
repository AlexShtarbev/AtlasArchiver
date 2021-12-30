package com.ashtarbev.atlas.archiver.data;

import com.ashtarbev.atlas.archiver.core.item.Item;
import com.ashtarbev.atlas.archiver.core.item.ItemType;
import com.ashtarbev.atlas.archiver.core.user.User;
import com.ashtarbev.atlas.archiver.core.user.UserMapper;
import com.ashtarbev.atlas.archiver.core.user.UserToStore;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemsRepositoryTest extends AbstractCockroachDbTest{

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserMapper userMapper;

    private final Random random = new Random();

    @After
    public void cleanup() {
        usersRepository.deleteAll();
        itemsRepository.deleteAll();
    }

    @Test
    public void shouldStoreItemForUser() {
        User user = usersRepository.save(userMapper.toUser(getUserToStore()));
        Item itemToStore = getItem(user.getId());
        Item storedItem = itemsRepository.save(itemToStore);
        Optional<Item> itemFromDB = itemsRepository.getItemById(storedItem.getId());
        assertThat(itemFromDB).isPresent();
        assertThat(itemFromDB.get()).isEqualTo(storedItem);
    }

    @Test
    public void shouldStoreAndRetrieveItemsPerUser() {
        final Map<User, List<Item>> expectedItems = new HashMap<>();
        final int numberOfUser = getNumberInBound(5, 20);
        for (int i = 0; i < numberOfUser; i++) {
            User user = usersRepository.save(userMapper.toUser(getUserToStore()));
            int numberOfItems = getNumberInBound(10, 30);
            List<Item> items = new ArrayList<>();
            for (int j = 0; j < numberOfItems; j++) {
                items.add(itemsRepository.save(getItem(user.getId())));
            }
            Collections.reverse(items);
            expectedItems.put(user, items);
        }

        expectedItems.forEach((user, items) -> {
            List<Item> itemsForUserFromDb = itemsRepository.getAllItemsForUser(user.getId());
            assertThat(itemsForUserFromDb).isEqualTo(items);
        });
    }

    @Test
    public void shouldDeleteItemById() {
        User user = usersRepository.save(userMapper.toUser(getUserToStore()));
        Item storedItem = itemsRepository.save(getItem(user.getId()));
        Optional<Item> itemFromDB = itemsRepository.getItemById(storedItem.getId());
        assertThat(itemFromDB).isPresent();
        itemsRepository.deleteItemById(itemFromDB.get().getId(), itemFromDB.get().getUserId());

        itemFromDB = itemsRepository.getItemById(storedItem.getId());
        assertThat(itemFromDB).isEmpty();
    }

    private Item getItem(long userId) {
        return Item.builder()
                .userId(userId)
                .imageUrl(getRandomUrl())
                .url(getRandomUrl())
                .type(ItemType.ARTICLE)
                .title(UUID.randomUUID().toString())
                .addedTimestamp(Timestamp.from(Instant.now()))
                .build();
    }

    private String getRandomUrl() {
        return String.format("https://%s.com", UUID.randomUUID());
    }

    private UserToStore getUserToStore() {
        return UserToStore.builder()
                .email(String.format("%s@test.com", UUID.randomUUID()))
                .name("test")
                .password("test")
                .build();
    }

    public int getNumberInBound(int lowerBound, int upperBound) {
        return random.nextInt(upperBound - lowerBound) + lowerBound;
    }
}
