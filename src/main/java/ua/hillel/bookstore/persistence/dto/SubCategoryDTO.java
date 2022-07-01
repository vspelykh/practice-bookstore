package ua.hillel.bookstore.persistence.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class SubCategoryDTO implements Serializable {
    private final Integer id;
    private final CategoryDTO category;
    private final String name;
}
