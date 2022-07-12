package ua.hillel.bookstore.persistence.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class WishlistItemDTO implements Serializable {
    private Integer id;
    private final UserDTO user;
    private final BookDTO book;
}