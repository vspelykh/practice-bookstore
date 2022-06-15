package ua.hillel.bookstore.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDTO implements Serializable {
    private final Integer id;
    private final String name;
}
