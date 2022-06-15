package ua.hillel.bookstore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ua.hillel.bookstore.dto.PublisherDTO;
import ua.hillel.bookstore.mapper.base.BaseMapperConfig;
import ua.hillel.bookstore.model.Publisher;

@Mapper(uses = BaseMapperConfig.class)
public interface PublisherMapper {
    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "name", source = "entity.name")
    PublisherDTO toDTO(Publisher entity);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "name", source = "dto.name")
    Publisher toEntity(PublisherDTO dto);
}
