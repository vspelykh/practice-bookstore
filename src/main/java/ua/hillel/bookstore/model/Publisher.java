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
@Table(name = "publishers")
@Getter
@Setter
public class Publisher extends AbstractBaseEntity implements Serializable {

    @Column(name = "publisher", nullable = false)
    @NotBlank
    @Size(min = 3, max = 120)
    private String name;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> books = new LinkedHashSet<>();

    public Publisher() {
    }

    public Publisher(String name) {
        this.name = name;
    }

    public Publisher(Integer id, String name) {
        super(id);
        this.name = name;
    }
}