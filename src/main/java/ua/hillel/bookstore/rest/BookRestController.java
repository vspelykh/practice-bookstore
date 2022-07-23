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
import ua.hillel.bookstore.persistence.dto.CartItemDTO;
import ua.hillel.bookstore.persistence.entity.Book;
import ua.hillel.bookstore.service.BookService;
import ua.hillel.bookstore.utils.ControllerUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

@RestController
@RequestMapping(path = "/api/books", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BookRestController {

    private final BookService service;

    @GetMapping(value = "/allQueryDSL")
    public ResponseEntity<Page<BookDTO>> search(HttpServletResponse response, List<Integer> categories,
                                                List<Integer> subcategories, List<Integer> publishers, String search,
                                                Integer minPrice, Integer maxPrice,
                                                Integer pageNumber, Integer pageSize, String sortBy
    ) {
        Predicate<Book> titleOrAuthor;
        if ((search == null) || search.equals("")) {
            titleOrAuthor = book -> true;
        } else {
            titleOrAuthor = book -> {
                String full = book.getTitle() + " " + book.getAuthor().getName();
                String[] split = search.split(" ");
                for (String from : split) {
                    if (!full.toLowerCase().contains(from.toLowerCase())) {
                        return false;
                    }
                }
                return true;
            };
        }
        Predicate<Book> categoriesPredicate;
        if (categories == null) {
            categoriesPredicate = book -> true;
        } else {
            categoriesPredicate = book -> {
                for (Integer category : categories) {
                    if (category.equals(book.getSubCategory().getCategory().getId())) {
                        return true;
                    }
                }
                return false;
            };
        }
        Predicate<Book> subcategoriesPredicate;
        if (subcategories == null) {
            subcategoriesPredicate = book -> true;
        } else {
            subcategoriesPredicate = book -> {
                for (Integer category : subcategories) {
                    if (category.equals(book.getSubCategory().getId())) {
                        return true;
                    }
                }
                return false;
            };
        }
        Predicate<Book> publisherPredicate;
        if (publishers == null) {
            publisherPredicate = book -> true;
        } else
            publisherPredicate = book -> {
                for (Integer category : publishers) {
                    if (category.equals(book.getPublisher().getId())) {
                        return true;
                    }
                }
                return false;
            };
        Predicate<Book> pricePredicate;
        if (minPrice == null && maxPrice == null) {
            pricePredicate = book -> true;
        } else if (minPrice == null) {
            pricePredicate = book -> book.getPrice().intValue() <= maxPrice;
        } else if (maxPrice == null) {
            pricePredicate = book -> book.getPrice().intValue() >= minPrice;
        } else {
            pricePredicate = book -> book.getPrice().intValue() >= minPrice && book.getPrice().intValue() <= maxPrice;
        }
        Sort sort = ControllerUtils.getSort(sortBy);
        Page<BookDTO> bookDTOPage = service.findAll(PageRequest.of(pageNumber - 1, pageSize, sort), titleOrAuthor,
                categoriesPredicate, subcategoriesPredicate, publisherPredicate, pricePredicate);
        ControllerUtils.addPageHeaders(response, bookDTOPage);
        return new ResponseEntity<>(bookDTOPage, HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<List<BookDTO>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
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
        for (BookDTO searched : service.getAll()) {
            if (searched.isAvailable() && !searched.equals(book)) {
                related.put(searched, service.getMarkOfEqual(searched, Objects.requireNonNull(book)));
            }
        }
        return service.getMostRelated(related);
    }

    public void editAmountAfterOrdering(List<CartItemDTO> cartItems) {
        for (CartItemDTO cartItem : cartItems) {
            BookDTO book = cartItem.getBook();
            int newAmount = book.getAmount() - cartItem.getQuantity();
            book.setAmount(newAmount);
            service.save(book);
        }
    }
}
