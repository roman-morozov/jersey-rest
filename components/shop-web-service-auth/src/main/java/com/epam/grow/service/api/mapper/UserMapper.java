package com.epam.grow.service.api.mapper;

import com.epam.grow.domain.UserEntity;
import org.mapstruct.Mapper;
import com.epam.grow.service.api.dto.User;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User convertEntityToDto(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    UserEntity convertDtoToEntity(User user);
}
