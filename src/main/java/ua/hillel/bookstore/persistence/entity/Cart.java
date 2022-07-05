package ua.hillel.bookstore.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@Getter
@Setter
public class Cart extends AbstractBaseEntity {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cart")
    private List<CartItem> items = new ArrayList<>();
}
