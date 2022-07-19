package ua.hillel.bookstore.persistence.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartItemDTO implements Serializable {
    private Integer id;
    private final UserDTO user;
    private final BookDTO book;
    private int quantity;

}
