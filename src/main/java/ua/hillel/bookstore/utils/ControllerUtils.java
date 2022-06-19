package ua.hillel.bookstore.utils;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import ua.hillel.bookstore.dto.BookDTO;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ControllerUtils {

    public static void addPageHeaders(HttpServletResponse response, Page<BookDTO> bookDTOPage) {
        response.addIntHeader("total-pages", bookDTOPage.getTotalPages());
        response.addIntHeader("total-count", Math.toIntExact(bookDTOPage.getTotalElements()));
    }
}
