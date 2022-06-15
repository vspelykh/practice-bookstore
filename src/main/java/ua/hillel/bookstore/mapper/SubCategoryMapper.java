package ua.hillel.bookstore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ua.hillel.bookstore.dto.SubCategoryDTO;
import ua.hillel.bookstore.mapper.base.BaseMapperConfig;
import ua.hillel.bookstore.model.SubCategory;

@Mapper(uses = {BaseMapperConfig.class, CategoryMapper.class})
public interface SubCategoryMapper {

    SubCategoryMapper INSTANCE = Mappers.getMapper(SubCategoryMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "category", source = "entity.category"),
            @Mapping(target = "name", source = "entity.name")
    })
    SubCategoryDTO toDTO(SubCategory entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "category", source = "dto.category"),
            @Mapping(target = "name", source = "dto.name")
    })
    SubCategory toEntity(SubCategoryDTO dto);
}
