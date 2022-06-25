package ua.hillel.bookstore.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category extends AbstractBaseEntity implements Serializable {

    @Column(name = "category", nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<SubCategory> subCategories = new LinkedHashSet<>();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(Integer id, String name) {
        super(id);
        this.name = name;
    }
}