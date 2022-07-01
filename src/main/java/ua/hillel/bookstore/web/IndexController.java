package ua.hillel.bookstore.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.hillel.bookstore.persistence.dto.BookDTO;
import ua.hillel.bookstore.rest.BookController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final BookController bookController;

    @GetMapping
    public String index(HttpServletResponse response, Model model){

        Page<BookDTO> list = bookController.search(response, null, 0,10, "title").getBody();
        model.addAttribute("books", list);
        return "index";
    }
}
