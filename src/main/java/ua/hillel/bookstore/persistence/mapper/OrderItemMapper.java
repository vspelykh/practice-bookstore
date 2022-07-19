package ua.hillel.bookstore.persistence.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.hillel.bookstore.persistence.dto.OrderItemDTO;
import ua.hillel.bookstore.persistence.entity.OrderItem;
import ua.hillel.bookstore.persistence.mapper.base.BaseMapperConfig;

@Mapper(componentModel = "spring", uses = BaseMapperConfig.class)
public interface OrderItemMapper {

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "order", source = "entity.order")
    @Mapping(target = "user", source = "entity.user")
    @Mapping(target = "book", source = "entity.book")
    @Mapping(target = "quantity", source = "entity.quantity")
    @Mapping(target = "price", source = "entity.price")
    OrderItemDTO toDTO(OrderItem entity);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "order", source = "dto.order")
    @Mapping(target = "user", source = "dto.user")
    @Mapping(target = "book", source = "dto.book")
    @Mapping(target = "quantity", source = "dto.quantity")
    @Mapping(target = "price", source = "dto.price")
    OrderItem toEntity(OrderItemDTO dto);
}