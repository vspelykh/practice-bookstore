package ua.hillel.bookstore.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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
    private String address;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "post", nullable = false)
    @NotBlank
    private String post;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDateTime date;

    @Column(name = "sum", nullable = false)
    private Integer sum;

    @Column(name = "comment")
    private String comment;

}
