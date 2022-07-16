package ua.hillel.bookstore.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.hillel.bookstore.persistence.dto.OrderDTO;
import ua.hillel.bookstore.persistence.entity.Order;
import ua.hillel.bookstore.persistence.mapper.base.BaseMapperConfig;

@Mapper(componentModel = "spring", uses = BaseMapperConfig.class)
public interface OrderMapper {

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "user", source = "entity.user")
    @Mapping(target = "address", source = "entity.address")
    @Mapping(target = "status", source = "entity.status")
    @Mapping(target = "post", source = "entity.post")
    @Mapping(target = "date", source = "entity.date")
    @Mapping(target = "sum", source = "entity.sum")
    @Mapping(target = "comment", source = "entity.comment")
    OrderDTO toDTO(Order entity);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "user", source = "dto.user")
    @Mapping(target = "address", source = "dto.address")
    @Mapping(target = "status", source = "dto.status")
    @Mapping(target = "post", source = "dto.post")
    @Mapping(target = "date", source = "dto.date")
    @Mapping(target = "sum", source = "dto.sum")
    @Mapping(target = "comment", source = "dto.comment")
    Order toEntity(OrderDTO dto);
}
