package ua.hillel.bookstore.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ua.hillel.bookstore.persistence.dto.BookDTO;
import ua.hillel.bookstore.persistence.dto.WishlistItemDTO;
import ua.hillel.bookstore.rest.BookRestController;
import ua.hillel.bookstore.rest.CartAndWishlistController;
import ua.hillel.bookstore.rest.CategoryController;
import ua.hillel.bookstore.rest.PublisherController;
import ua.hillel.bookstore.utils.SecurityUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookRestController bookRestController;
    private final CategoryController categoryController;
    private final PublisherController publisherController;
    private final CartAndWishlistController cartAndWishlistController;

    @GetMapping
    public String index(@RequestParam(required = false, value = "search") String search,
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
        model.addAttribute("cartCapacity", cartAndWishlistController.getCapacity(1));
        return "index";
    }

    @GetMapping("/book/{id}")
    public String bookInfo(@PathVariable("id") int id, Model model){
        ResponseEntity<BookDTO> responseBook = bookRestController.getById(id);
        if (responseBook.getStatusCode().is2xxSuccessful()){
            model.addAttribute("book", responseBook.getBody());
        } else {
            //TODO: handling exception
        }

        return "book";
    }

    @GetMapping("/cart")
    public String cart(Model model){

        model.addAttribute("items", cartAndWishlistController.getCartItems(SecurityUtil.getFakeAuthUserId()));
        return "cart";
    }

    @GetMapping("/wishlist")
    public String wishlist(Model model){

        List<WishlistItemDTO> booksFromWishlist = cartAndWishlistController.getBooksFromWishlist(SecurityUtil.getFakeAuthUserId());
        model.addAttribute("isEmpty", booksFromWishlist.isEmpty());
        model.addAttribute("wishlist", booksFromWishlist);
        return "wishlist";
    }
}
