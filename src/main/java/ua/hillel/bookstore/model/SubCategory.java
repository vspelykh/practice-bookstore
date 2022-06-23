package ua.hillel.bookstore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "sub_categories")
@Getter
@Setter
public class SubCategory extends AbstractBaseEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "sub_category", nullable = false)
    @NotBlank
    @Size(min = 3, max = 120)
    private String name;

    @OneToMany(mappedBy = "subCategory")
    private Set<Book> books = new LinkedHashSet<>();

    public SubCategory() {
    }

    public SubCategory(Category category, String name) {
        this.category = category;
        this.name = name;
    }

    public SubCategory(Integer id, Category category, String name) {
        super(id);
        this.category = category;
        this.name = name;
    }
}