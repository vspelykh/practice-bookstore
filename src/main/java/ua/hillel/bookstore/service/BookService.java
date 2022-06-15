package ua.hillel.bookstore.service;

import org.springframework.stereotype.Service;
import ua.hillel.bookstore.dto.BookDTO;
import ua.hillel.bookstore.mapper.BookMapper;
import ua.hillel.bookstore.model.Book;
import ua.hillel.bookstore.repository.book.BookRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService extends GenericQueryDSL<Book> {

    private final BookRepository bookRepository;
    private final BookMapper mapper;

    public BookService(BookRepository bookRepository, BookMapper mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    public List<BookDTO> getAllBooks(){
        List<BookDTO> bookDTOS = new ArrayList<>();
        bookRepository.getAll().forEach(book -> bookDTOS.add(mapper.toDTO(book)));
        return bookDTOS;
    }

    public BookDTO getByVendorCode(int vendorCode){
        return bookRepository.getByVendorCode(vendorCode);
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
}
