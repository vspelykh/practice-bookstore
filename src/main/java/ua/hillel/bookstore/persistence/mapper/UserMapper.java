package ua.hillel.bookstore.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.hillel.bookstore.persistence.dto.UserDTO;
import ua.hillel.bookstore.persistence.entity.User;
import ua.hillel.bookstore.persistence.mapper.base.BaseMapperConfig;

@Mapper(componentModel = "spring", uses = BaseMapperConfig.class)
public interface UserMapper {

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "name", source = "entity.name")
    @Mapping(target = "surname", source = "entity.surname")
    @Mapping(target = "email", source = "entity.email")
    @Mapping(target = "role", source = "entity.role")
    UserDTO toDTO(User entity);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "surname", source = "dto.surname")
    @Mapping(target = "email", source = "dto.email")
    @Mapping(target = "role", source = "dto.role")
    User toEntity(UserDTO dto);
}
