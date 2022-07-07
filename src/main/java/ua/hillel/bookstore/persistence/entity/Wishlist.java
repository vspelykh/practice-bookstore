package ua.hillel.bookstore.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "wishlists")
@Getter
@Setter
public class Wishlist extends AbstractBaseEntity {


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "wishlist")
    private Set<WishlistItem> wishlistItems = new java.util.LinkedHashSet<>();
}
