package ua.hillel.bookstore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book extends AbstractBaseEntity implements Serializable {

    @Column(name = "vendor_code", nullable = false)
    private Integer vendorCode;

    @Column(name = "title", nullable = false)
    @NotBlank
    @Size(min = 3, max = 120)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @Column(name = "pages", nullable = false)
    private Integer pages;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sub_category_id", nullable = false)
    private SubCategory subCategory;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cover_id", nullable = false)
    private Cover cover;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "cover_image_url", nullable = false)
    private String coverImageUrl;

    public Book() {
    }

    public Book(Integer vendorCode, String title, Author author, Publisher publisher, Integer pages, SubCategory subCategory,
                Language language, Cover cover, Integer year, BigDecimal price, String coverImageUrl) {
        this.vendorCode = vendorCode;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.pages = pages;
        this.subCategory = subCategory;
        this.language = language;
        this.cover = cover;
        this.year = year;
        this.price = price;
        this.coverImageUrl = coverImageUrl;
    }

    public Book(Integer id, Integer vendorCode, String title, Author author, Publisher publisher, Integer pages,
                SubCategory subCategory, Language language, Cover cover, Integer year, BigDecimal price,
                String coverImageUrl) {
        super(id);
        this.vendorCode = vendorCode;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.pages = pages;
        this.subCategory = subCategory;
        this.language = language;
        this.cover = cover;
        this.year = year;
        this.price = price;
        this.coverImageUrl = coverImageUrl;
    }
}