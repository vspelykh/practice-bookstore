package ua.hillel.bookstore.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServiceHttpHeaders {
    TOTAL_COUNT("total-count"),
    TOTAL_PAGES("total-pages"),
    PAGE_NUMBER("page-number"),
    PAGE_SIZE("page-size");

    private final String name;
}
