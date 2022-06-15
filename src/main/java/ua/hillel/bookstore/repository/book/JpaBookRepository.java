package ua.hillel.bookstore.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.hillel.bookstore.dto.BookDTO;
import ua.hillel.bookstore.model.Book;

@Transactional(readOnly = true)
public interface JpaBookRepository extends JpaRepository<Book, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Book b WHERE b.id=:id")
    int delete(@Param("id") int id);

    BookDTO findByVendorCode(int vendorCode);
}