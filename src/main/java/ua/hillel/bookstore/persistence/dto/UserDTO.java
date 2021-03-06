package ua.hillel.bookstore.persistence.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    private final Integer id;
    private final String name;
    private final String surname;
    private final String email;
    private final String role;
}

