package ua.hillel.bookstore.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import ua.hillel.bookstore.persistence.dto.BookDTO;
import ua.hillel.bookstore.persistence.dto.CartItemDTO;
import ua.hillel.bookstore.persistence.dto.WishlistItemDTO;
import ua.hillel.bookstore.rest.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequiredArgsConstructor
public class BookController {

    private final CartController cartController;
    private final BookRestController bookRestController;
    private final CategoryController categoryController;
    private final PublisherController publisherController;
    private final WishlistController wishlistController;


    /* Method left to use with QueryDSL. Need to implement and test which solution work better*/
    @GetMapping("/home")
    public String home(@RequestParam(required = false, value = "search") String search,
                       @RequestParam(name = "page", required = false, defaultValue = "1") Integer pageNumber,
                       @RequestParam(name = "size", required = false, defaultValue = "20") Integer pageSize,
                       @RequestParam(name = "sort", required = false, defaultValue = "title") String sortBy,
                       HttpServletResponse response, Model model) {

        Page<BookDTO> page = bookRestController.search(response, search, pageNumber, pageSize, sortBy).getBody();
        if (Objects.requireNonNull(page).getTotalPages() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, page.getTotalPages())
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("sizes", CollectionUtils.arrayToList(new int[]{2, 5, 10, 15, 20}));
        model.addAttribute("size", pageSize);
        model.addAttribute("books", page);
        model.addAttribute("categories", categoryController.getCategories().getBody());
        model.addAttribute("publishers", publisherController.getAll(null).getBody());
        model.addAttribute("cartCapacity", cartController.getCapacity());
        model.addAttribute("cartSum", cartController.getCartSum());
        return "home";
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", bookRestController.getAll().getBody());
        model.addAttribute("categories", categoryController.getCategories().getBody());
        model.addAttribute("publishers", publisherController.getAll(null).getBody());
        model.addAttribute("cartCapacity", cartController.getCapacity());
        model.addAttribute("cartSum", cartController.getCartSum());
        return "index";
    }

    @GetMapping("/book/{id}")
    public String bookInfo(@PathVariable("id") int id, Model model) {
        ResponseEntity<BookDTO> responseBook = bookRestController.getById(id);
        if (responseBook.getStatusCode().is2xxSuccessful()) {
            model.addAttribute("book", responseBook.getBody());
            List<BookDTO> relatedBooks = bookRestController.getRelatedBooks(id);
            model.addAttribute("related1", relatedBooks.subList(0, 5));
            model.addAttribute("related2", relatedBooks.subList(5, 10));
        } else {
            //TODO: handling exception
        }

        return "book";
    }

    @GetMapping("/cart")
    public String cart(Model model) {

        model.addAttribute("isEmpty", cartController.getCartItems().isEmpty());
        model.addAttribute("cartSum", cartController.getCartSum());
        model.addAttribute("items", cartController.getCartItems());
        return "cart";
    }

    @PostMapping("/cart")
    public String editQuantity(@ModelAttribute("id") int id, @ModelAttribute("quantity") Integer quantity,
                               @ModelAttribute("action") String action) {

        if ("edit".equals(action)) {
            cartController.editQuantity(id, quantity);
        } else if ("add".equals(action)) {
            BookDTO book = bookRestController.getById(id).getBody();
            List<CartItemDTO> cartItems = cartController.getCartItems();
            for (CartItemDTO itemDTO : cartItems) {
                if (itemDTO.getBook().equals(book)) {
                    return "redirect:cart";
                }
            }
            cartController.addItemToCart(book);
        } else if ("delete".equals(action)) {
            cartController.deleteFromCart(id);
        }
        return "redirect:cart";
    }

    @GetMapping("/wishlist")
    public String wishlist(Model model) {

        List<WishlistItemDTO> booksFromWishlist = wishlistController.getBooksFromWishlist();
        model.addAttribute("isEmpty", booksFromWishlist.isEmpty());
        model.addAttribute("wishlist", booksFromWishlist);
        return "wishlist";
    }

    @PostMapping("/wishlist")
    public String editWishlist(@ModelAttribute("id") int id, @ModelAttribute("action") String action) {
        if ("add".equals(action)) {
            wishlistController.addToWishlist(bookRestController.getById(id).getBody());
            return "redirect:wishlist";
        } else if ("delete".equals(action)) {
            wishlistController.removeFromWishlist(id);
        }
        return "redirect:wishlist";
    }
}
