package ua.hillel.bookstore.repository.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.hillel.bookstore.model.Author;

@Transactional(readOnly = true)
public interface JpaAuthorRepository extends JpaRepository<Author, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Author a WHERE a.id=:id")
    int delete(@Param("id") int id);
}