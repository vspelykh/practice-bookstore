package ua.hillel.bookstore.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ua.hillel.bookstore.persistence.dto.CategoryDTO;
import ua.hillel.bookstore.persistence.entity.Category;
import ua.hillel.bookstore.persistence.mapper.base.BaseMapperConfig;

@Mapper(componentModel = "spring",uses = BaseMapperConfig.class)
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "entity.name")
    CategoryDTO toDTO(Category entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "dto.name")
    Category toEntity(CategoryDTO dto);
}
