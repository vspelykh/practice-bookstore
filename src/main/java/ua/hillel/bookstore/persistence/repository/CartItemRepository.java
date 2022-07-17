package ua.hillel.bookstore.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.hillel.bookstore.persistence.entity.CartItem;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    List<CartItem> getByUserId(int userId);
}
