package ua.hillel.bookstore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ua.hillel.bookstore.dto.LanguageDTO;
import ua.hillel.bookstore.mapper.base.BaseMapperConfig;
import ua.hillel.bookstore.model.Language;

@Mapper(uses = BaseMapperConfig.class)
public interface LanguageMapper {

    LanguageMapper INSTANCE = Mappers.getMapper(LanguageMapper.class);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "name", source = "entity.name")
    LanguageDTO toDTO(Language entity);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "name", source = "dto.name")
    Language toEntity(LanguageDTO dto);
}