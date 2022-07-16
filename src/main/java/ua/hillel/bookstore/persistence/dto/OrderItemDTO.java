package ua.hillel.bookstore.persistence.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderItemDTO implements Serializable {
    private final Integer id;
    private final OrderDTO order;
    private final UserDTO user;
    private final BookDTO book;
    private final Integer price;
}
