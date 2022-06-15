package ua.hillel.bookstore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
@Getter
@Setter
public class Author extends AbstractBaseEntity implements Serializable {

    @Column(name = "author", nullable = false)
    @NotBlank
    @Size(min = 3, max = 120)
    private String name;

    @OneToMany(mappedBy = "author")
    private Set<Book> books = new LinkedHashSet<>();

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public Author(Integer id, String name) {
        super(id);
        this.name = name;
    }
}