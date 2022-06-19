package ua.hillel.bookstore.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.hillel.bookstore.dto.BookDTO;
import ua.hillel.bookstore.mapper.BookMapper;
import ua.hillel.bookstore.model.Book;
import ua.hillel.bookstore.repository.book.BookRepository;

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
        return mapper.toDTO(bookRepository.get(id));
    }

    public boolean delete(Integer id) {
        return bookRepository.delete(id);
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
