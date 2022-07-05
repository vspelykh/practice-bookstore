package ua.hillel.bookstore.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.hillel.bookstore.persistence.dto.BookDTO;
import ua.hillel.bookstore.persistence.dto.CartDTO;
import ua.hillel.bookstore.persistence.dto.CategoryDTO;
import ua.hillel.bookstore.rest.BookController;
import ua.hillel.bookstore.rest.CartController;
import ua.hillel.bookstore.rest.CategoryController;
import ua.hillel.bookstore.rest.PublisherController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final BookController bookController;
    private final CategoryController categoryController;
    private final PublisherController publisherController;
    private final CartController cartController;

    @GetMapping
    public String index(@RequestParam(required = false, value = "search") String search,
                        @RequestParam(value = "categories-chosen", required = false) List<CategoryDTO> categoriesChosen,
                        @RequestParam(name = "page", required = false, defaultValue = "1") Integer pageNumber,
                        @RequestParam(name = "size", required = false, defaultValue = "20") Integer pageSize,
                        @RequestParam(name = "sort", required = false, defaultValue = "title") String sortBy,
                        HttpServletResponse response, Model model) {

        Page<BookDTO> page = bookController.search(response, search, pageNumber, pageSize, sortBy).getBody();
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
        model.addAttribute("subCategories", categoryController.getSubCategories(categoriesChosen).getBody());
        model.addAttribute("publishers", publisherController.getAll(null).getBody());
        model.addAttribute("cartCapacity", cartController.getCapacity(1));
        return "index";
    }

    @GetMapping("/cart")
    public String cart(Model model){

        model.addAttribute("items", cartController.getCartItems(1));
        return "cart";
    }
}
