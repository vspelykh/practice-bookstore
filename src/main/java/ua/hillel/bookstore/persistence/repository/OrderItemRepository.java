package ua.hillel.bookstore.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.hillel.bookstore.persistence.entity.OrderItem;
import ua.hillel.bookstore.persistence.entity.Status;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> getAllByOrderId(int id);
}