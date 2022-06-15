package ua.hillel.bookstore.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hillel.bookstore.dto.*;
import ua.hillel.bookstore.mapper.*;
import ua.hillel.bookstore.model.*;
import ua.hillel.bookstore.repository.book.BookRepository;
import ua.hillel.bookstore.service.BookService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final BookService bookService;


    @GetMapping("/sss")
    public ResponseEntity<List<BookDTO>> getAlls(){

        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Book>> getAllBooks(
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

        List<Book> books = bookRepository.getAll();
        books = books.stream().
                filter(book -> vendorCode == null || book.getVendorCode().toString().contains(vendorCode.toString())).
                filter(book -> title == null || book.getTitle().contains(title)).
                filter(book -> author == null || AuthorMapper.INSTANCE.toDTO(book.getAuthor()).equals(author)).
                filter(book -> publisher == null || PublisherMapper.INSTANCE.toDTO(book.getPublisher()).equals(publisher)).
                filter(book -> pagesFrom == null || book.getPages() >= pagesFrom).
                filter(book -> pagesTo == null || book.getPages() <= pagesTo).
                filter(book -> subCategory == null || SubCategoryMapper.INSTANCE.toDTO(book.getSubCategory()).equals(subCategory)).
                filter(book -> language == null || LanguageMapper.INSTANCE.toDTO(book.getLanguage()).equals(language)).
                filter(book -> cover == null || CoverMapper.INSTANCE.toDTO(book.getCover()).equals(cover)).
                filter(book -> year == null || book.getYear().equals(year)).
                filter(book -> priceFrom == null || book.getPrice().intValue() >= priceFrom.intValue()).
                filter(book -> priceTo == null || book.getPrice().intValue() <= priceTo.intValue()).
                collect(Collectors.toList());
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Integer id) {
        return bookRepository.get(id) != null
                ? new ResponseEntity<>(bookRepository.get(id), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public boolean delById(@PathVariable Integer id) {
        return bookRepository.delete(id);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book){
       bookRepository.save(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable Integer id){

        if (bookRepository.get(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Book currentBook = bookRepository.get(id);
        if (book.getVendorCode() != null )
            currentBook.setVendorCode(book.getVendorCode());
        if (book.getTitle() != null)
            currentBook.setTitle(book.getTitle());
        if (book.getAuthor() != null)
            currentBook.setAuthor(book.getAuthor());
        if (book.getPublisher() != null)
            currentBook.setPublisher(book.getPublisher());
        if (book.getPages() != null)
            currentBook.setPages(book.getPages());
        if (book.getSubCategory() != null)
            currentBook.setSubCategory(book.getSubCategory());
        if (book.getLanguage() != null)
            currentBook.setLanguage(book.getLanguage());
        if (book.getCover() != null)
            currentBook.setCover(book.getCover());
        if (book.getYear() != null)
            currentBook.setYear(book.getYear());
        if (book.getPages() != null)
            currentBook.setPages(book.getPages());
        if (book.getCoverImageUrl() != null) {
            currentBook.setCoverImageUrl(book.getCoverImageUrl());
        }
        bookRepository.save(currentBook);
        return new ResponseEntity<>(currentBook, HttpStatus.OK);
    }
}
