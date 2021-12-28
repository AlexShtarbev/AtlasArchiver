package com.ashtarbev.atlas.archiver.service.user;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashtarbev.atlas.archiver.core.user.User;
import com.ashtarbev.atlas.archiver.core.user.UserMapper;
import com.ashtarbev.atlas.archiver.core.user.UserToStore;
import com.ashtarbev.atlas.archiver.data.UsersRepository;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UsersRepository usersRepository;
  private final UserMapper userMapper;

  public UserController(UsersRepository usersRepository, UserMapper userMapper) {
    this.usersRepository = usersRepository;
    this.userMapper = userMapper;
  }

  @PostMapping("")
  public HttpEntity<String> createUser(@RequestBody UserToStore userToStore) {
    try {
      usersRepository.save(userMapper.toUser(userToStore));
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity
          .internalServerError()
          .body("An error occurred while trying to store the user");
    }
  }

  @GetMapping("/all")
  public HttpEntity<List<User>> getAllUsers() {
    try {
      List<User> allUsers = usersRepository.getAllUsers();
      return ResponseEntity.ok().body(allUsers);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }
}
