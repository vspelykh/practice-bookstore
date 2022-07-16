package ua.hillel.bookstore.persistence.dto;

import lombok.Data;
import ua.hillel.bookstore.persistence.entity.Role;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    private final Integer id;
    private final String name;
    private final String surname;
    private final String role;
}

