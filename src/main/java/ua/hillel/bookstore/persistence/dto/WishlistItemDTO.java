package ua.hillel.bookstore.persistence.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class WishlistItemDTO implements Serializable {
    private Integer id;
    private final WishlistDTO wishlist;
    private final BookDTO book;
}