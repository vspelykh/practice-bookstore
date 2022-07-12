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
    UserDTO toDTO(User entity);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "name", source = "dto.name")
    User toEntity(UserDTO dto);
}
