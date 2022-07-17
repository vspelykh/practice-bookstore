package ua.hillel.bookstore.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends AbstractBaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "user")
    private List<WishlistItem> wishlistItems = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private Set<CartItem> items = new HashSet<>();

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    Role role;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 128)
    private String password;

}
