package ua.hillel.bookstore.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.hillel.bookstore.persistence.dto.BookDTO;
import ua.hillel.bookstore.persistence.dto.CategoryDTO;
import ua.hillel.bookstore.rest.BookController;
import ua.hillel.bookstore.rest.CategoryController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final BookController bookController;
    private final CategoryController categoryController;

    @GetMapping
    public String index(@RequestParam(value = "categories-chosen", required = false) List<CategoryDTO> categoriesChosen,
                        HttpServletResponse response, Model model) {

        Page<BookDTO> list = bookController.search(response, null, 0, 10, "title").getBody();
        model.addAttribute("books", list);
        model.addAttribute("categories", categoryController.getCategories().getBody());
        model.addAttribute("subCategories", categoryController.getSubCategories(categoriesChosen).getBody());
        return "index";
    }
}
