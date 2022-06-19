package ua.hillel.bookstore.utils;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import ua.hillel.bookstore.constant.ServiceHttpHeaders;
import ua.hillel.bookstore.dto.BookDTO;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ControllerUtils {

    public static void addPageHeaders(HttpServletResponse response, Page<BookDTO> bookDTOPage) {

        response.setHeader(ServiceHttpHeaders.TOTAL_COUNT.getName(), String.valueOf(bookDTOPage.getTotalElements()));
        response.setHeader(ServiceHttpHeaders.TOTAL_PAGES.getName(), String.valueOf(bookDTOPage.getTotalPages()));
        response.setHeader(ServiceHttpHeaders.PAGE_NUMBER.getName(), String.valueOf(bookDTOPage.getNumber()));
        response.setHeader(ServiceHttpHeaders.PAGE_SIZE.getName(), String.valueOf(bookDTOPage.getSize()));
    }

    public static Sort getSort(String sortBy) {
        String[] split = sortBy.split(",");
        List<Sort.Order> orders = new ArrayList<>();
        if (split.length == 1) {
            return Sort.by(sortBy);
        }
        Sort sort;
        for (int i = 1; i < split.length; i++) {
            if (split[i].equals("asc")) {
                orders.add(new Sort.Order(Sort.Direction.ASC, split[i - 1]));
                i++;
            } else if (split[i].equals("desc")) {
                orders.add(new Sort.Order(Sort.Direction.DESC, split[i - 1]));
                i++;
            } else
                orders.add(new Sort.Order(Sort.Direction.ASC, split[i - 1]));
        }
        sort = Sort.by(orders);
        return sort;
    }
}
