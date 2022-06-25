package ua.hillel.bookstore.persistence.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class PublisherDTO implements Serializable {
    private final Integer id;
    @NotBlank
    @Size(min = 3, max = 120)
    private final String name;
}
