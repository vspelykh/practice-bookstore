package ua.hillel.bookstore.persistence.dto;

import lombok.Data;
import ua.hillel.bookstore.persistence.entity.Status;

import java.io.Serializable;

@Data
public class OrderDTO implements Serializable {
    private final Integer id;
    private final UserDTO user;
    private final String address;
    private final String status;
    private final String post;
}
