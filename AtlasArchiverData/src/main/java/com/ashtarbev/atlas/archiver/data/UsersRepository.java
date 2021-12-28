package com.ashtarbev.atlas.archiver.data;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ashtarbev.atlas.archiver.core.user.User;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
  @Query("SELECT * FROM users ORDER BY id ASC")
  List<User> getAllUsers();

  @Modifying
  @Query("DELETE FROM users")
  void deleteAll();
}
