package ua.hillel.bookstore.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.hillel.bookstore.persistence.dto.WishlistItemDTO;
import ua.hillel.bookstore.persistence.entity.WishlistItem;
import ua.hillel.bookstore.persistence.mapper.base.BaseMapperConfig;

@Mapper(componentModel = "spring", uses = BaseMapperConfig.class)
public interface WishlistItemMapper {

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "wishlist", source = "entity.wishlist")
    @Mapping(target = "book", source = "entity.book")
    WishlistItemDTO toDTO(WishlistItem entity);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "wishlist", source = "dto.wishlist")
    @Mapping(target = "book", source = "dto.book")
    WishlistItem toEntity(WishlistItemDTO dto);
}
