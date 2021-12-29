package com.ashtarbev.atlas.archiver.data;

import com.ashtarbev.atlas.archiver.core.user.User;
import com.ashtarbev.atlas.archiver.core.user.UserMapper;
import com.ashtarbev.atlas.archiver.core.user.UserToStore;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class UsersRepositoryTest extends AbstractCockroachDbTest {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserMapper userMapper;

    @After
    public void after() {
        usersRepository.deleteAll();
    }

    @Test
    public void shouldSaveUser() {
        User user = usersRepository.save(userMapper.toUser(getUserToStore()));
        Optional<User> userFromDB = usersRepository.getUserById(user.getId());
        assertThat(userFromDB).isPresent();
        assertThat(user).isEqualTo(userFromDB.get());
    }

    @Test
    public void shouldGetAllUsers() {
        final int numberOfUsers = 10;
        final List<User> users = new ArrayList<>();
        for (int i = 0; i < numberOfUsers; i++) {
            users.add(usersRepository.save(userMapper.toUser(getUserToStore())));
        }
        List<User> allSavedUsers = usersRepository.getAllUsers();
        assertThat(allSavedUsers).isNotEmpty();
        assertThat(allSavedUsers.size()).isEqualTo(numberOfUsers);
        assertThat(allSavedUsers).isEqualTo(users);
    }

    private UserToStore getUserToStore() {
        return UserToStore.builder()
                .email(String.format("%s@test.com", UUID.randomUUID()))
                .name("test")
                .password("test")
                .build();
    }
}
