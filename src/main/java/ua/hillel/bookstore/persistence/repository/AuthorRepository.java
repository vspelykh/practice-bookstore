package ua.hillel.bookstore.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.hillel.bookstore.persistence.entity.Author;

@Repository
@Transactional(readOnly = true)
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Author a WHERE a.id=:id")
    int delete(@Param("id") int id);
}