package ua.hillel.bookstore.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.hillel.bookstore.persistence.dto.CartDTO;
import ua.hillel.bookstore.persistence.entity.Cart;
import ua.hillel.bookstore.persistence.mapper.base.BaseMapperConfig;

@Mapper(componentModel = "spring", uses = BaseMapperConfig.class)
public interface CartMapper {

    @Mapping(target = "id", source = "entity.id")
    CartDTO toDTO(Cart entity);

    @Mapping(target = "id", source = "dto.id")
    Cart toEntity(CartDTO dto);
}
