package ua.hillel.bookstore.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.hillel.bookstore.persistence.dto.CartItemDTO;
import ua.hillel.bookstore.persistence.entity.CartItem;
import ua.hillel.bookstore.persistence.mapper.base.BaseMapperConfig;

@Mapper(componentModel = "spring", uses = BaseMapperConfig.class)
public interface CartItemMapper {

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "cart", source = "entity.cart")
    @Mapping(target = "book", source = "entity.book")
    @Mapping(target = "quantity", source = "entity.quantity")
    CartItemDTO toDTO(CartItem entity);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "cart", source = "dto.cart")
    @Mapping(target = "book", source = "dto.book")
    @Mapping(target = "quantity", source = "dto.quantity")
    CartItem toEntity(CartItemDTO dto);
}
