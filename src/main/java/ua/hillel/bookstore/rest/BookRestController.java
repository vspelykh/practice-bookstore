package ua.hillel.bookstore.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hillel.bookstore.persistence.dto.BookDTO;
import ua.hillel.bookstore.persistence.entity.Book;
import ua.hillel.bookstore.service.BookService;
import ua.hillel.bookstore.utils.BookPredicatesBuilder;
import ua.hillel.bookstore.utils.ControllerUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping(path = "/rest/books", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BookRestController {

    private final BookService service;

    @GetMapping(value = "/all")
    public ResponseEntity<Page<BookDTO>> search(HttpServletResponse response, String search, Integer pageNumber,
                                                Integer pageSize, String sortBy
    ) {
        Page<BookDTO> bookDTOPage;
        Sort sort = ControllerUtils.getSort(sortBy);
        BookPredicatesBuilder builder = new BookPredicatesBuilder();
        if (search != null) {
            Pattern pattern = Pattern.compile("([a-zA-Z.]+?)([:<>])([a-zA-Z ]+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
            }
            bookDTOPage = service.findAll(builder.build(), PageRequest.of(pageNumber - 1, pageSize, sort));
        } else {
            bookDTOPage = service.findAll(PageRequest.of(pageNumber - 1, pageSize, sort));
        }
        ControllerUtils.addPageHeaders(response, bookDTOPage);
        return new ResponseEntity<>(bookDTOPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getById(@PathVariable Integer id) {
        return service.get(id) != null
                ? new ResponseEntity<>(service.get(id), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public boolean delById(@PathVariable Integer id) {
        return service.delete(id);
    }

    @PostMapping
    public Book createOrEditBook(@RequestBody BookDTO book) {
        return service.save(book);
    }

    public List<BookDTO> getRelatedBooks(int id) {

        BookDTO book = getById(id).getBody();
        Map<BookDTO, Integer> related = new HashMap<>();
        for (BookDTO searched : service.getAll()){
            if (searched.isAvailable() && !searched.equals(book)){
                related.put(searched, service.getMarkOfEqual(searched, Objects.requireNonNull(book)));
            }
        }
        return service.getMostRelated(related);
    }
}
