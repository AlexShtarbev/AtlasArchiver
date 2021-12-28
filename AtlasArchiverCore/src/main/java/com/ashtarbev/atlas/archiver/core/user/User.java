package com.ashtarbev.atlas.archiver.core.user;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Table("users")
@NoArgsConstructor
@AllArgsConstructor
public class User extends UserBase {
  @Id
  long id;
}
