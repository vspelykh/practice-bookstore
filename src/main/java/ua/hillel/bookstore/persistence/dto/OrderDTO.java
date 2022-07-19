package ua.hillel.bookstore.persistence.dto;

import lombok.Data;
import ua.hillel.bookstore.persistence.entity.Status;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class OrderDTO implements Serializable {
    private final Integer id;
    private final UserDTO user;
    private final String address;
    private Status status;
    private final String post;
    private final LocalDateTime date;
    private Integer sum;
    private final String comment;
}
