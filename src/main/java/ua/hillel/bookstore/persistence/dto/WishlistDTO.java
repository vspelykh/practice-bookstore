package ua.hillel.bookstore.persistence.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class WishlistDTO implements Serializable {
    private final Integer id;
}
