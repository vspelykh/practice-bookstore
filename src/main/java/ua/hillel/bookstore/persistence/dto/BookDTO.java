package ua.hillel.bookstore.persistence.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class BookDTO implements Serializable {
    private final Integer id;
    private final Integer vendorCode;
    @NotBlank
    @Size(min = 3, max = 120)
    private final String title;
    private final AuthorDTO author;
    private final PublisherDTO publisher;
    private final Integer pages;
    private final SubCategoryDTO subCategory;
    private final LanguageDTO language;
    private final CoverDTO cover;
    private final Integer year;
    private final BigDecimal price;
    private final String description;
    private final int amount;
    private final String coverImageUrl;

    public boolean isAvailable(){
        return amount > 0;
    }
}
