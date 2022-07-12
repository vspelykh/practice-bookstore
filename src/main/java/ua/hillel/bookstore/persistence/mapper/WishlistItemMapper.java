package ua.hillel.bookstore.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.hillel.bookstore.persistence.dto.WishlistItemDTO;
import ua.hillel.bookstore.persistence.entity.WishlistItem;
import ua.hillel.bookstore.persistence.mapper.base.BaseMapperConfig;

@Mapper(componentModel = "spring", uses = BaseMapperConfig.class)
public interface WishlistItemMapper {

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "user", source = "entity.user")
    @Mapping(target = "book", source = "entity.book")
    WishlistItemDTO toDTO(WishlistItem entity);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "user", source = "dto.user")
    @Mapping(target = "book", source = "dto.book")
    WishlistItem toEntity(WishlistItemDTO dto);
}
