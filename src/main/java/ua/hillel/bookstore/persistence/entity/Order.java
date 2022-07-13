package ua.hillel.bookstore.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order extends AbstractBaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(name = "address", nullable = false)
    @NotBlank
    String address;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    Status status;

    @Column(name = "post", nullable = false)
    @NotBlank
    String post;

}
