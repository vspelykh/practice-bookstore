package ua.hillel.bookstore.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
@Getter
@Setter
public class OrderItem extends AbstractBaseEntity {
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "price", nullable = false)
    Integer price;

}