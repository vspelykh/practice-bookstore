package ua.hillel.bookstore.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends AbstractBaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "user")
    private List<WishlistItem> wishlistItems = new ArrayList<>();

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    Role role;

}
