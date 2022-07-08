package ua.hillel.bookstore.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import ua.hillel.bookstore.rest.BookRestController;
import ua.hillel.bookstore.rest.CategoryController;
import ua.hillel.bookstore.rest.PublisherController;

@Controller("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final BookRestController bookRestController;
    private final CategoryController categoryController;
    private final PublisherController publisherController;

//TODO
}
