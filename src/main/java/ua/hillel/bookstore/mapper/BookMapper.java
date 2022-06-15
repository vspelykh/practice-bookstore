package ua.hillel.bookstore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ua.hillel.bookstore.dto.BookDTO;
import ua.hillel.bookstore.mapper.base.BaseMapperConfig;
import ua.hillel.bookstore.model.Book;

@Mapper(uses = {BaseMapperConfig.class, AuthorMapper.class, PublisherMapper.class, SubCategoryMapper.class,
        LanguageMapper.class, CoverMapper.class})
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "vendorCode", source = "entity.vendorCode")
    @Mapping(target = "title", source = "entity.title")
    @Mapping(target = "author", source = "entity.author")
    @Mapping(target = "publisher", source = "entity.publisher")
    @Mapping(target = "pages", source = "entity.pages")
    @Mapping(target = "subCategory", source = "entity.subCategory")
    @Mapping(target = "language", source = "entity.language")
    @Mapping(target = "cover", source = "entity.cover")
    BookDTO toDTO(Book entity);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "vendorCode", source = "dto.vendorCode")
    @Mapping(target = "title", source = "dto.title")
    @Mapping(target = "author", source = "dto.author")
    @Mapping(target = "publisher", source = "dto.publisher")
    @Mapping(target = "pages", source = "dto.pages")
    @Mapping(target = "subCategory", source = "dto.subCategory")
    @Mapping(target = "language", source = "dto.language")
    @Mapping(target = "cover", source = "dto.cover")
    Book toEntity(BookDTO dto);
}
