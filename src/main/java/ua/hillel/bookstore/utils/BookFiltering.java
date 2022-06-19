package ua.hillel.bookstore.utils;

import lombok.Data;

@Data
public class BookFiltering {

    private String title;

    private Integer categoryId;

    private Integer subCategoryId;

    private String author;

    private String publisher;
}
