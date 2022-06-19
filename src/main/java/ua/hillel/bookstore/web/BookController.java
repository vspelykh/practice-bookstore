package ua.hillel.bookstore.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hillel.bookstore.dto.BookDTO;
import ua.hillel.bookstore.model.Book;
import ua.hillel.bookstore.service.BookService;
import ua.hillel.bookstore.utils.BookPredicatesBuilder;
import ua.hillel.bookstore.utils.ControllerUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping(path = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @GetMapping(value = "/all")
    public ResponseEntity<Page<BookDTO>> search(
            HttpServletResponse response,
            @RequestParam(required = false, value = "search") String search,
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize,
            @RequestParam(required = false, defaultValue = "title") String sortBy
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
            bookDTOPage = service.findAll(builder.build(), PageRequest.of(pageNumber, pageSize, sort));
        } else {
            bookDTOPage = service.findAll(PageRequest.of(pageNumber, pageSize, sort));
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
}
