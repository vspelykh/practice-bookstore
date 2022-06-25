package ua.hillel.bookstore.persistence.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CoverDTO implements Serializable {
    private final Integer id;
    private final String type;
}
