package ua.hillel.bookstore.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "wishlist_items")
@Getter
@Setter
public class WishlistItem extends AbstractBaseEntity {

    @ManyToOne
    @JoinColumn(name = "wishlist_id")
    private Wishlist wishlist;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;
}