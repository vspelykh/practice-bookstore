package ua.hillel.bookstore.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends AbstractBaseEntity {

    private String name;

    @OneToMany(mappedBy = "user")
    private List<WishlistItem> wishlistItems = new ArrayList<>();

}
