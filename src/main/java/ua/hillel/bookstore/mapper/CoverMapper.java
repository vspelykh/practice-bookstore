package ua.hillel.bookstore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ua.hillel.bookstore.dto.CoverDTO;
import ua.hillel.bookstore.mapper.base.BaseMapperConfig;
import ua.hillel.bookstore.model.Cover;

@Mapper(componentModel = "spring",uses = BaseMapperConfig.class)
public interface CoverMapper {

    CoverMapper INSTANCE = Mappers.getMapper(CoverMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "type", source = "entity.type")
    CoverDTO toDTO(Cover entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "type", source = "dto.type")
    Cover toEntity(CoverDTO dto);
}
