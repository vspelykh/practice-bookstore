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
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;
}