package ua.hillel.bookstore.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.hillel.bookstore.persistence.dto.BookDTO;
import ua.hillel.bookstore.persistence.dto.CartItemDTO;
import ua.hillel.bookstore.persistence.dto.WishlistItemDTO;
import ua.hillel.bookstore.rest.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequiredArgsConstructor
public class BookController {

    private final CartController cartController;
    private final AuthorController authorController;
    private final BookRestController bookRestController;
    private final CategoryController categoryController;
    private final PublisherController publisherController;
    private final WishlistController wishlistController;
    private final CharacteristicController characteristicController;

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
        model.addAttribute("subCategories", categoryController.getSubCategories(null).getBody());
        model.addAttribute("publishers", publisherController.getAll(null).getBody());
        model.addAttribute("cartCapacity", cartController.getCapacity());
        model.addAttribute("cartSum", cartController.getCartSum());
        return "home";
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", bookRestController.getAll().getBody());
        model.addAttribute("subCategories", categoryController.getSubCategories(null).getBody());
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

    @PostMapping("/book")
    public String editBook(RedirectAttributes redirectAttributes, @ModelAttribute("id") int id, @ModelAttribute("action") String action) {
        if ("delete".equals(action)) {
            bookRestController.delById(id);
            return "redirect:admin";
        } else if ("edit".equals(action)) {
            redirectAttributes.addAttribute("id", id);
            redirectAttributes.addAttribute("action", action);
            return "forward:/create/book";
        }
        return null;
    }

    @PostMapping("/form/book")
    public String bookForm(Model model, @ModelAttribute("action") String action, @ModelAttribute("id") Integer id) {
        model.addAttribute("authors", authorController.getAllAuthors(null).getBody());
        model.addAttribute("publishers", publisherController.getAll(null).getBody());
        model.addAttribute("subCategories", categoryController.getSubCategories(null).getBody());
        model.addAttribute("languages", characteristicController.getAllLanguages().getBody());
        model.addAttribute("covers", characteristicController.getAllCovers().getBody());
        model.addAttribute("action", action);
        if (action.equals("edit")) {
            model.addAttribute("book", bookRestController.getById(id).getBody());
            return "bookForm";
        } else {
            return "createBookForm";
        }
    }

    @PostMapping("/edit/book")
    public String editBook(@ModelAttribute("id") Integer id, @ModelAttribute("vendor_code") Integer vendorCode,
                           @ModelAttribute("title") String title, @ModelAttribute("author") Integer author,
                           @ModelAttribute("publisher") Integer publisher, @ModelAttribute("pages") Integer pages,
                           @ModelAttribute("subCategory") Integer subCategory, @ModelAttribute("cover") Integer cover,
                           @ModelAttribute("language") Integer language, @ModelAttribute("year") Integer year,
                           @ModelAttribute("price") Integer price, @ModelAttribute("description") String description,
                           @ModelAttribute("amount") Integer amount, @ModelAttribute("coverImageUrl") String coverImageUrl) {

        BookDTO bookDTO;
        if (id == -1) {
            bookDTO = new BookDTO(null, vendorCode, title, authorController.getById(author).getBody(), publisherController.getById(publisher).getBody(),
                    pages, categoryController.getSubCategoryById(subCategory), characteristicController.getLanguageById(language),
                    characteristicController.getCoverById(cover), year, description, coverImageUrl);
        } else {
            bookDTO = new BookDTO(id, vendorCode, title, authorController.getById(author).getBody(), publisherController.getById(publisher).getBody(),
                    pages, categoryController.getSubCategoryById(subCategory), characteristicController.getLanguageById(language),
                    characteristicController.getCoverById(cover), year, description, coverImageUrl);
        }
        bookDTO.setPrice(new BigDecimal(price));
        bookDTO.setAmount(amount);
        bookRestController.createOrEditBook(bookDTO);
        return "redirect:/admin";
    }

    @PostMapping("/book/editAmount")
    public String editAmount(@ModelAttribute("id") Integer id, @ModelAttribute("amount") Integer amount) {
        BookDTO book = bookRestController.getById(id).getBody();
        Objects.requireNonNull(book).setAmount(amount);
        bookRestController.createOrEditBook(book);
        return "redirect:/admin";
    }

    @PostMapping("/book/editPrice")
    public String editPrice(@ModelAttribute("id") Integer id, @ModelAttribute("price") Integer price) {
        BookDTO book = bookRestController.getById(id).getBody();
        Objects.requireNonNull(book).setPrice(new BigDecimal(price));
        bookRestController.createOrEditBook(book);
        return "redirect:/admin";
    }


    @GetMapping("/cart")
    public String cart(Model model) {
        List<CartItemDTO> cartItems = cartController.getCartItems();
        for (CartItemDTO item : cartItems) {
            if (item.getBook().getAmount() == 0) {
                cartController.deleteFromCart(item.getId());
                cartItems.remove(item);
            } else if (item.getQuantity() > item.getBook().getAmount()) {
                cartController.editQuantity(item.getId(), item.getBook().getAmount());
                cartItems.add(item);
            }
        }
        model.addAttribute("isEmpty", cartItems.isEmpty());
        model.addAttribute("cartSum", cartController.getCartSum());
        model.addAttribute("items", cartItems);
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
