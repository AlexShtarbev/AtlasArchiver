package com.ashtarbev.atlas.archiver.data;

import com.ashtarbev.atlas.archiver.core.user.UserMapper;
import com.ashtarbev.atlas.archiver.core.user.UserToStore;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class UsersTest extends AbstractCockroachDbTest {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserMapper userMapper;

    @After
    public void after() {
        usersRepository.deleteAll();
    }

    @Test
    public void f() throws IOException, InterruptedException {
        System.out.println("[TEST] ContainerId = " + cockroachDbContainer.getContainerId());
        usersRepository.save(userMapper.toUser(getUserToStore()));
//        List<User> allUsers = usersRepository.getAllUsers();
//        System.out.println(allUsers);
    }

    @Test
    public void f2() throws IOException, InterruptedException {
        System.out.println("[TEST] ContainerId = " + cockroachDbContainer.getContainerId());
        usersRepository.save(userMapper.toUser(getUserToStore()));
//        List<User> allUsers = usersRepository.getAllUsers();
//        System.out.println(allUsers);
    }

    private UserToStore getUserToStore() {
        return UserToStore.builder()
                .email("test@test.com")
                .name("test")
                .password("test")
                .build();
    }
}
