package ua.hillel.bookstore.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.hillel.bookstore.persistence.dto.BookDTO;
import ua.hillel.bookstore.persistence.entity.Book;
import ua.hillel.bookstore.persistence.mapper.base.BaseMapperConfig;

@Mapper(componentModel = "spring", uses = {BaseMapperConfig.class, AuthorMapper.class, PublisherMapper.class, SubCategoryMapper.class,
        LanguageMapper.class, CoverMapper.class})
public interface BookMapper {

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "vendorCode", source = "entity.vendorCode")
    @Mapping(target = "title", source = "entity.title")
    @Mapping(target = "author", source = "entity.author")
    @Mapping(target = "publisher", source = "entity.publisher")
    @Mapping(target = "pages", source = "entity.pages")
    @Mapping(target = "subCategory", source = "entity.subCategory")
    @Mapping(target = "language", source = "entity.language")
    @Mapping(target = "cover", source = "entity.cover")
    @Mapping(target = "year", source = "entity.year")
    @Mapping(target = "price", source = "entity.price")
    @Mapping(target = "description", source = "entity.description")
    @Mapping(target = "amount", source = "entity.amount")
    @Mapping(target = "coverImageUrl", source = "entity.coverImageUrl")
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
    @Mapping(target = "year", source = "dto.year")
    @Mapping(target = "price", source = "dto.price")
    @Mapping(target = "description", source = "dto.description")
    @Mapping(target = "amount", source = "dto.amount")
    @Mapping(target = "coverImageUrl", source = "dto.coverImageUrl")
    Book toEntity(BookDTO dto);
}
