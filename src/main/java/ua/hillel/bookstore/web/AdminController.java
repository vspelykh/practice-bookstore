package ua.hillel.bookstore.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.hillel.bookstore.rest.BookRestController;
import ua.hillel.bookstore.rest.CategoryController;
import ua.hillel.bookstore.rest.PublisherController;

@Controller()
@RequiredArgsConstructor
public class AdminController {

    private final BookRestController bookRestController;
    private final CategoryController categoryController;
    private final PublisherController publisherController;

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("books", bookRestController.getAll().getBody());

        return "admin";
    }

}
