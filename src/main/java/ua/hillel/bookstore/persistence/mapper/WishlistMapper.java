package ua.hillel.bookstore.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.hillel.bookstore.persistence.dto.WishlistDTO;
import ua.hillel.bookstore.persistence.entity.Wishlist;
import ua.hillel.bookstore.persistence.mapper.base.BaseMapperConfig;

@Mapper(componentModel = "spring", uses = BaseMapperConfig.class)
public interface WishlistMapper {

    @Mapping(target = "id", source = "entity.id")
    WishlistDTO toDTO(Wishlist entity);

    @Mapping(target = "id", source = "dto.id")
    Wishlist toEntity(WishlistDTO dto);
}
