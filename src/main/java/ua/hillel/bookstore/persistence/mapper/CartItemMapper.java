package ua.hillel.bookstore.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.hillel.bookstore.persistence.dto.CartItemDTO;
import ua.hillel.bookstore.persistence.entity.CartItem;
import ua.hillel.bookstore.persistence.mapper.base.BaseMapperConfig;

@Mapper(componentModel = "spring", uses = BaseMapperConfig.class)
public interface CartItemMapper {

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "user", source = "entity.user")
    @Mapping(target = "book", source = "entity.book")
    @Mapping(target = "quantity", source = "entity.quantity")
    CartItemDTO toDTO(CartItem entity);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "user", source = "dto.user")
    @Mapping(target = "book", source = "dto.book")
    @Mapping(target = "quantity", source = "dto.quantity")
    CartItem toEntity(CartItemDTO dto);
}
