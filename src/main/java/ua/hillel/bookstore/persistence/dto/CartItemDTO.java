package ua.hillel.bookstore.persistence.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartItemDTO implements Serializable {
    private Integer id;
    private final CartDTO cart;
    private final BookDTO book;
    private final int quantity;

}
