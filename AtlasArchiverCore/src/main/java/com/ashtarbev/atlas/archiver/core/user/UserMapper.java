package com.ashtarbev.atlas.archiver.core.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {
  @Mapping(target = "id", ignore = true)
  User toUser(UserToStore userToStore);
}
