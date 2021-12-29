package com.ashtarbev.atlas.archiver.service.user;

import com.ashtarbev.atlas.archiver.core.user.User;
import com.ashtarbev.atlas.archiver.core.user.UserMapper;
import com.ashtarbev.atlas.archiver.core.user.UserToStore;
import com.ashtarbev.atlas.archiver.data.UsersRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
  private final UsersRepository usersRepository;
  private final UserMapper userMapper;

  public UsersController(UsersRepository usersRepository, UserMapper userMapper) {
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
