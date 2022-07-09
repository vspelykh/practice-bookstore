package ua.hillel.bookstore.persistence.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubCategoryDTO implements Serializable {
    private final Integer id;
    private final CategoryDTO category;
    private final String name;
}
