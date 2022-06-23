package ua.hillel.bookstore.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hillel.bookstore.dto.*;
import ua.hillel.bookstore.model.Book;
import ua.hillel.bookstore.service.BookService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @GetMapping(path = "/all")
    public ResponseEntity<List<BookDTO>> getAllBooks(
            @RequestParam(required = false) Integer vendorCode,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) AuthorDTO author,
            @RequestParam(required = false) PublisherDTO publisher,
            @RequestParam(required = false) Integer pagesFrom,
            @RequestParam(required = false) Integer pagesTo,
            @RequestParam(required = false) SubCategoryDTO subCategory,
            @RequestParam(required = false) LanguageDTO language,
            @RequestParam(required = false) CoverDTO cover,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) BigDecimal priceFrom,
            @RequestParam(required = false) BigDecimal priceTo) {

        List<BookDTO> books = service.getAllBooks();
        books = books.stream().
                filter(book -> vendorCode == null || book.getVendorCode().toString().contains(vendorCode.toString())).
                filter(book -> title == null || book.getTitle().contains(title)).
                filter(book -> author == null || book.getAuthor().equals(author)).
                filter(book -> publisher == null || book.getPublisher().equals(publisher)).
                filter(book -> pagesFrom == null || book.getPages() >= pagesFrom).
                filter(book -> pagesTo == null || book.getPages() <= pagesTo).
                filter(book -> subCategory == null || book.getSubCategory().equals(subCategory)).
                filter(book -> language == null || book.getLanguage().equals(language)).
                filter(book -> cover == null || book.getCover().equals(cover)).
                filter(book -> year == null || book.getYear().equals(year)).
                filter(book -> priceFrom == null || book.getPrice().intValue() >= priceFrom.intValue()).
                filter(book -> priceTo == null || book.getPrice().intValue() <= priceTo.intValue()).
                collect(Collectors.toList());
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getById(@PathVariable Integer id) {
        return service != null
                ? new ResponseEntity<>(service.get(id), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public boolean delById(@PathVariable Integer id) {
        return service.delete(id);
    }

    @PostMapping
    public Book createOrEditBook(@RequestBody BookDTO book){
       return service.save(book);
    }
}
