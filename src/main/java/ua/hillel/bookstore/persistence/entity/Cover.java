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
@Table(name = "covers")
@Getter
@Setter
public class Cover extends AbstractBaseEntity implements Serializable {

    @Column(name = "type", nullable = false)
    private String type;

    @OneToMany(mappedBy = "cover")
    private Set<Book> books = new LinkedHashSet<>();

    public Cover() {
    }

    public Cover(String type) {
        this.type = type;
    }

    public Cover(Integer id, String type) {
        super(id);
        this.type = type;
    }
}