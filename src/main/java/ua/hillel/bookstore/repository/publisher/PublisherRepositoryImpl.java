package ua.hillel.bookstore.repository.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ua.hillel.bookstore.model.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PublisherRepositoryImpl implements PublisherRepository{

    @PersistenceContext
    private EntityManager em;

    private final JpaPublisherRepository repository;

    @Override
    public Publisher save(Publisher publisher) {
        if (publisher.isNew()) {
            em.persist(publisher);
            return publisher;
        } else {
            return em.merge(publisher);
        }
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public List<Publisher> getAll() {
        return repository.findAll();
    }

    @Override
    public Publisher get(int id) {
        return repository.findById(id).orElse(null);
    }
}
