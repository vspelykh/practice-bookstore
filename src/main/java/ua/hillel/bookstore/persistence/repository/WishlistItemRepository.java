package ua.hillel.bookstore.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.hillel.bookstore.persistence.entity.WishlistItem;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface WishlistItemRepository extends JpaRepository<WishlistItem, Integer> {

    List<WishlistItem> findAllByUserId(int userId);

    void deleteAllByUserId(int userId);
}