package ua.hillel.bookstore.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.hillel.bookstore.persistence.dto.BookDTO;
import ua.hillel.bookstore.persistence.entity.Book;
import ua.hillel.bookstore.persistence.mapper.BookMapper;
import ua.hillel.bookstore.persistence.repository.BookRepository;

import javax.transaction.Transactional;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        if (!bookRepository.existsById(id)) {
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

    public Page<BookDTO> findAll(Pageable pageable, Predicate<Book> authorOrTitle, Predicate<Book> categoryPredicate,
                                 Predicate<Book> subcategoryPredicate, Predicate<Book> publisherPredicate,
                                 Predicate<Book> pricePredicate) {
        List<BookDTO> books = bookRepository.findAll().stream().filter(authorOrTitle).filter(categoryPredicate)
                .filter(subcategoryPredicate).filter(publisherPredicate).filter(pricePredicate).map(mapper::toDTO)
                .collect(Collectors.toList());
        /*Class PageImpl works not correct. That's why I use this algorithm:*/
        int totalPages = books.size() / pageable.getPageSize();
        int indexFrom = pageable.getPageSize() * pageable.getPageNumber();
        int indexTo;
        if (pageable.getPageNumber() == totalPages) {
            indexTo = books.size();
        } else {
            indexTo = indexFrom + pageable.getPageSize();
        }
        return new PageImpl<>(books.subList(indexFrom, indexTo), pageable, books.size());
    }

    public Page<BookDTO> findAll(PageRequest of) {
        Page<Book> entities = bookRepository.findAll(of);
        return entities.map(mapper::toDTO);
    }

    public List<BookDTO> getAll() {
        return bookRepository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public Integer getMarkOfEqual(BookDTO searched, BookDTO book) {
        if (searched.equals(book)) {
            return 0;
        }
        int mark = 0;
        if (searched.getAuthor().equals(book.getAuthor())) {
            mark += 3;
        }
        if (searched.getPublisher().equals(book.getPublisher())) {
            mark += 2;
        }
        if (searched.getSubCategory().equals(book.getSubCategory())) {
            mark += 4;
        }
        if (searched.getSubCategory().getCategory().equals(book.getSubCategory().getCategory())) {
            mark += 2;
        }
        String[] split1 = searched.getTitle().split(" ");
        String[] split2 = book.getTitle().split(" ");
        for (String s1 : split1) {
            for (String s2 : split2) {
                if (s1.contains(s2) || s2.contains(s1)) {
                    mark++;
                }
                if (s1.equals(s2)) {
                    mark += 2;
                }
            }
        }
        return mark;
    }

    public List<BookDTO> getMostRelated(Map<BookDTO, Integer> related) {
        List<Map.Entry<BookDTO, Integer>> list = new ArrayList<>(related.entrySet());
        list.sort(Map.Entry.comparingByValue((o1, o2) -> o2 - o1));

        Map<BookDTO, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<BookDTO, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        List<BookDTO> relatedList = new LinkedList<>();
        int i = 0;
        while (i < 10) {
            for (Map.Entry<BookDTO, Integer> entry : result.entrySet()) {
                relatedList.add(entry.getKey());
                i++;
            }
        }
        return relatedList;
    }
}
