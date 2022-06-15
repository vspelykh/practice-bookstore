package ua.hillel.bookstore.repository.author;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.hillel.bookstore.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository{

    @PersistenceContext
    private EntityManager em;

    private final JpaAuthorRepository repository;

    public AuthorRepositoryImpl(JpaAuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Author save(Author author) {
        if (author.isNew()) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public List<Author> getAll() {
        return repository.findAll();
    }

    @Override
    public Author get(int id) {
        return repository.findById(id).orElse(null);
    }
}
