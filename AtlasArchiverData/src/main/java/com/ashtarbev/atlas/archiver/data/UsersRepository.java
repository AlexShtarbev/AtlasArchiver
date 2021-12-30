package com.ashtarbev.atlas.archiver.data;

import com.ashtarbev.atlas.archiver.core.user.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {

  @Query("SELECT * FROM users WHERE id = :id")
  Optional<User> getUserById(@Param("id") long id);

  @Query("SELECT * FROM users ORDER BY id ASC")
  List<User> getAllUsers();

  @Modifying
  @Query("DELETE FROM users WHERE id = :id")
  void deleteUserById(@Param("id") long id);

  @Modifying
  @Query("DELETE FROM users")
  void deleteAll();
}
