package ua.hillel.bookstore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ua.hillel.bookstore.dto.CategoryDTO;
import ua.hillel.bookstore.mapper.base.BaseMapperConfig;
import ua.hillel.bookstore.model.Category;

@Mapper(uses = BaseMapperConfig.class)
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "entity.name")
    CategoryDTO toDTO(Category entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "dto.name")
    Category toEntity(CategoryDTO dto);
}
