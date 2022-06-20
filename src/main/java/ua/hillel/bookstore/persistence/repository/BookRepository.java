package ua.hillel.bookstore.persistence.repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.hillel.bookstore.persistence.entity.Book;
import ua.hillel.bookstore.persistence.entity.QBook;

@Repository
@Transactional(readOnly = true)
public interface BookRepository extends JpaRepository<Book, Integer>,
        QuerydslPredicateExecutor<Book>, QuerydslBinderCustomizer<QBook> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Book b WHERE b.id=:id")
    int delete(@Param("id") int id);

    default void customize(
            QuerydslBindings bindings, QBook root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        bindings.excluding(root.coverImageUrl);
    }
}