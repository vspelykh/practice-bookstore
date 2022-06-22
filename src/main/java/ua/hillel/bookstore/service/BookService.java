package ua.hillel.bookstore.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.hillel.bookstore.persistence.dto.BookDTO;
import ua.hillel.bookstore.persistence.entity.Book;
import ua.hillel.bookstore.persistence.mapper.BookMapper;
import ua.hillel.bookstore.persistence.repository.BookRepository;

import javax.transaction.Transactional;

@Service
public class BookService extends GenericQueryDSL<Book> {

    private final BookRepository bookRepository;
    private final BookMapper mapper;

    public BookService(BookRepository bookRepository, BookMapper mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    public BookDTO get(Integer id) {
        return mapper.toDTO(bookRepository.getReferenceById(id));
    }

    public boolean delete(Integer id) {
        if (!bookRepository.existsById(id)){
            return false;
        }
        bookRepository.deleteById(id);
        return true;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Book save(BookDTO book) {
        final Book entity = mapper.toEntity(book);
        return bookRepository.save(entity);
    }

    public Page<BookDTO> findAll(BooleanExpression build, Pageable pageable) {
        Page<Book> entities = bookRepository.findAll(build, pageable);
        return entities.map(mapper::toDTO);
    }

    public Page<BookDTO> findAll(PageRequest of) {
        Page<Book> entities = bookRepository.findAll(of);
        return entities.map(mapper::toDTO);
    }
}
